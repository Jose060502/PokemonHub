package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.PokeModel
import kotlinx.coroutines.flow.Flow

// Interfaz que define las operaciones que pueden realizarse sobre los datos de los Pokémon
interface PokeInterface {

    // Función suspendida que inserta un Pokémon en el repositorio o base de datos.
    // La palabra clave `suspend` indica que es una función asincrónica que no bloqueará el hilo principal.
    suspend fun insert(pokemon: PokeModel)

    // Función suspendida que elimina un Pokémon del repositorio o base de datos.
    // Al igual que `insert`, esta operación es asincrónica y no bloquea el hilo principal.
    suspend fun delete(pokemon: PokeModel)

    // Propiedad que expone un flujo reactivo (Flow) de una lista de todos los Pokémon almacenados.
    // `Flow` permite emitir valores de manera continua, lo cual es útil para escuchar cambios en los datos en tiempo real.
    // La vista (UI) se suscribiría a este flujo para recibir actualizaciones automáticas cuando los Pokémon cambien.
    val getAllPokemons: Flow<List<PokeModel>>
}