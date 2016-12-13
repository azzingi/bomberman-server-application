package model

/**
 * storage class to store Players
 * Created by Nathanael on 01.11.2016.
 */
class Player(val name: String, start: Tile) {
    var isAlive: Boolean = false
    var tile: Tile? = null
        private set

    init {
        tile = start
    }

    fun move(tile: Tile) {
        this.tile = tile
    }

    fun placeBomb() {

    }
}
