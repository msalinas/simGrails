package com.sim.prestamo

class MenuPrestamoController {
	
    static navigation = [
        group: 'tabs', action: 'inicioPrestamo',title: 'Préstamos', order: 50,
		subItems: [
			[action:'irPrestamoIndividual', order:0, title:"Individual"],
			[action:'irPrestamoGrupal', order:10, title:'Grupal']
        ]
	]
	
	def inicioPrestamo = { }
	
}
