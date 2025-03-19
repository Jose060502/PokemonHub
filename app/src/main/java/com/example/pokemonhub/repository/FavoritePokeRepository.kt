package com.example.pokemonhub.repository

import com.example.pokemonhub.datamodel.PokeModel
import com.example.pokemonhub.localdatabase.PokeDAO
import kotlinx.coroutines.flow.Flow

// **Repositorio para la gestión de Pokémon favoritos** (Capa de datos en MVVM)
// - Implementa la interfaz `PokeInterface` para definir las operaciones disponibles.
class FavoritePokeRepository(
    private val pokeDAO: PokeDAO
) : PokeInterface {

    // **Inserta un Pokémon en la lista de favoritos**
    // - Se ejecuta en una corrutina para evitar bloqueos en el hilo principal.
    override suspend fun insert(pokemon: PokeModel) = pokeDAO.insert(pokemon)

    // **Elimina un Pokémon de la lista de favoritos**
    override suspend fun delete(pokemon: PokeModel) = pokeDAO.delete(pokemon)

    // **Obtiene todos los Pokémon favoritos en tiempo real**
    // - `Flow` permite que la UI reciba actualizaciones automáticas cuando cambien los datos.
    override val getAllPokemons: Flow<List<PokeModel>> = pokeDAO.getAllPokemons()
}
