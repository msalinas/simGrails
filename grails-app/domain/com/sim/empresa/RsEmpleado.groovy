package com.sim.empresa

import com.sim.usuario.SecUser
import com.sim.catalogo.SimCatPuesto
import com.sim.catalogo.SimCatPerfil
import com.sim.regional.SimSucursal

class RsEmpleado {
	
	RsConfEmpresa rsConfEmpresa
	
	SecUser usuario
	
	static mapping = {
		usuario lazy:false
	}
	
	static belongsTo = [puesto : SimCatPuesto, perfil : SimCatPerfil, sucursalPertenece :  SimSucursal ]

    static constraints = {
		usuario(nullable:false)
		puesto(nullable: true)
		perfil(nullable: true)
		sucursalPertenece(nullable: true)
		rsConfEmpresa(nullable: false)
    }
}
