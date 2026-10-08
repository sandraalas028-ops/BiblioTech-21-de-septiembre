package com.example.bibliotech.viewmodel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibliotech.BibliotecaApplication
import com.example.bibliotech.model.Estudiante
import com.example.bibliotech.model.libro
import com.example.bibliotech.model.Prestamo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PrestamoViewModel(aplication: Application) : AndroidViewModel(aplication) {

    private val prestamoRepository =
        (aplication as BibliotecaApplication).prestamoRepository

    private val libroRepository =
        (aplication as BibliotecaApplication).libroRepository

    private val estudianteRepository =
        (aplication as BibliotecaApplication).estudianteRepository

    // Estados de UI
    private val _librosDisponibles = MutableStateFlow<List<libro>>(emptyList())
    val librosDisponibles: StateFlow<List<libro>> = _librosDisponibles.asStateFlow()


    private val _estudiantesActivos = MutableStateFlow<List<Estudiante>>(emptyList())
    val estudiantesActivos: StateFlow<List<Estudiante>> = _estudiantesActivos.asStateFlow()



    // ---------------------------------------------------------
    // PRÉSTAMOS ACTIVOS
    // ---------------------------------------------------------


    private val _prestamos =
        MutableStateFlow<List<Prestamo>>(emptyList())


    val prestamos: StateFlow<List<Prestamo>> =
        _prestamos.asStateFlow()


    private val _prestamoGuardado = MutableStateFlow<Boolean>(false)

    val prestamoGuardado: StateFlow<Boolean> =
        _prestamoGuardado.asStateFlow()


   /* fun cargarDatos() {
        viewModelScope.launch(Dispatchers.IO) {
            val libros = libroRepository.obtenerLibros()
            _librosDisponibles.value = libros.filter { it.disponible }

            val estudiantes = estudianteRepository.obtenerEstudiantes()
            _estudiantesActivos.value = estudiantes.filter { it.activo }

            _prestamosActivos.value = prestamoRepository.obtenerPrestamosActivos()
        }
    }

    fun registrarPrestamo(libroId: Int, estudianteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val libro = libroRepository.obtenerLibroPorId(libroId)
            if (libro == null || !libro.disponible) {
                return@launch
            }

            val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val nuevoPrestamo = Prestamo(
                idLibro = libroId,
                idEstudiante = estudianteId,
                fechaPrestamo = fechaActual,
                fechaDevolucion = null,
                devuelto = false
            )


            prestamoRepository.insertar(nuevoPrestamo)

            val libroActualizado = libro.copy(disponible = false)
            libroRepository.actualizarLibro(libroActualizado)

            val librosActualizados = libroRepository.obtenerLibros()
            _librosDisponibles.value = librosActualizados.filter { it.disponible }
            _prestamosActivos.value = prestamoRepository.obtenerPrestamosActivos()
            _prestamoGuardado.value = true
        }
    }

    fun reiniciarEstadoGuardado() {
        _prestamoGuardado.value = false
    } */

    fun registrarPrestamo(
        idLibro: Int,
        idEstudiante: Int

    ) {


        viewModelScope.launch(Dispatchers.IO) {


            // Buscamos el libro seleccionado.
            val libro =
                libroRepository.obtenerLibroPorId(idLibro)


            // Verificamos que el libro exista y esté disponible.
            if (libro == null || !libro.disponible) {
                return@launch
            }


            // Obtenemos la fecha actual.
            val fechaActual =
                SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                ).format(Date())


            // Creamos el nuevo préstamo.
            val nuevoPrestamo = Prestamo(
                idLibro = idLibro,
                idEstudiante = idEstudiante,
                fechaPrestamo = fechaActual,
                fechaDevolucion = null,
                devuelto = false
            )


            // Guardamos el préstamo.
            prestamoRepository.insertar(
                nuevoPrestamo
            )


            // El libro deja de estar disponible.
            val libroActualizado =
                libro.copy(
                    disponible = false
                )


            libroRepository.actualizarLibro(
                libroActualizado
            )


            // Actualizamos las listas.
            val librosActualizados =
                libroRepository.obtenerLibros()


            _librosDisponibles.value =
                librosActualizados.filter { it.disponible }


            _prestamos.value =
                prestamoRepository.obtenerPrestamosActivos()


            // Indicamos que el registro terminó correctamente.
            _prestamoGuardado.value = true
        }
    }




    // ---------------------------------------------------------
    // REINICIAR ESTADO DE GUARDADO
    // ---------------------------------------------------------


    fun reiniciarEstadoGuardado() {
        _prestamoGuardado.value = false
    }










    // NUEVOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO


    // ------
    // ---------------------------------------------------------
    // LIBROS RELACIONADOS CON LOS PRÉSTAMOS
    // ---------------------------------------------------------


    private val _librosPrestados =
        MutableStateFlow<Map<Int, libro>>(emptyMap())


    val librosPrestados: StateFlow<Map<Int, libro>> =
        _librosPrestados.asStateFlow()




    // ---------------------------------------------------------
    // ESTUDIANTES RELACIONADOS CON LOS PRÉSTAMOS
    // ---------------------------------------------------------


    private val _estudiantesPrestamos =
        MutableStateFlow<Map<Int, Estudiante>>(emptyMap())


    val estudiantesPrestamos: StateFlow<Map<Int, Estudiante>> =
        _estudiantesPrestamos.asStateFlow()


    // ---------------------------------------------------------
    // CARGAR DATOS
    // ---------------------------------------------------------


    fun cargarDatos() {


        viewModelScope.launch(Dispatchers.IO) {


            // Obtenemos todos los libros.
            val libros =
                libroRepository.obtenerLibros()


            // Dejamos únicamente los disponibles.
            _librosDisponibles.value =
                libros.filter {
                    it.disponible
                }


            // Obtenemos todos los estudiantes.
            val estudiantes =
                estudianteRepository.obtenerEstudiantes()


            // Dejamos únicamente los activos.
            _estudiantesActivos.value =
                estudiantes.filter {
                    it.activo
                }


            // Obtenemos los préstamos activos.
            val prestamos =
                prestamoRepository.obtenerPrestamosActivos()


            _prestamos.value = prestamos


            // -------------------------------------------------
            // PREPARAMOS LOS LIBROS DE LOS PRÉSTAMOS
            // -------------------------------------------------


            val mapaLibros =
                mutableMapOf<Int, libro>()


            prestamos.forEach { prestamo ->


                val libro =
                    libroRepository.obtenerLibroPorId(
                        prestamo.idLibro
                    )


                if (libro != null) {
                    mapaLibros[prestamo.idLibro] =
                        libro
                }
            }


            _librosPrestados.value =
                mapaLibros




            // -------------------------------------------------
            // PREPARAMOS LOS ESTUDIANTES DE LOS PRÉSTAMOS
            // -------------------------------------------------


            val mapaEstudiantes =
                mutableMapOf<Int, Estudiante>()


            prestamos.forEach { prestamo ->


                val estudiante =
                    estudianteRepository.obtenerEstudiantePorId(
                        prestamo.idEstudiante
                    )


                if (estudiante != null) {
                    mapaEstudiantes[prestamo.idEstudiante] =
                        estudiante
                }
            }


            _estudiantesPrestamos.value =
                mapaEstudiantes
        }
    }


    // ---------------------------------------------------------
    // DEVOLVER LIBRO
    // ---------------------------------------------------------


    fun devolverPrestamo(
        prestamo: Prestamo
    ) {


        viewModelScope.launch(Dispatchers.IO) {


            // Fecha actual de la devolución.
            val fechaActual =
                SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                ).format(Date())


            // Marcamos el préstamo como devuelto.
            val prestamoActualizado =
                prestamo.copy(
                    devuelto = true,
                    fechaDevolucion = fechaActual
                )


            prestamoRepository.actualizarPrestamo(
                prestamoActualizado
            )


            // Buscamos el libro asociado.
            val libro =
                libroRepository.obtenerLibroPorId(
                    prestamo.idLibro
                )


            // Volvemos a poner el libro como disponible.
            if (libro != null) {


                val libroActualizado =
                    libro.copy(
                        disponible = true
                    )


                libroRepository.actualizarLibro(
                    libroActualizado
                )
            }


            // Actualizamos la lista de préstamos activos.
            _prestamos.value =
                prestamoRepository.obtenerPrestamosActivos()


            // Actualizamos también los libros disponibles.
            val librosActualizados =
                libroRepository.obtenerLibros()


            _librosDisponibles.value =
                librosActualizados.filter {
                    it.disponible
                }
        }
    }


}

