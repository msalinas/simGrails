package com.sim.producto

class SimProductoController {

	def scaffold = true

    static navigation = [
        group: 'tabs', action: 'list',title: 'Productos', order: 10,
    ]

}
