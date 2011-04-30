package com.sim.empresa

//import com.sim.usuario.User
import com.sim.catalogo.SimCatPuesto
import com.sim.catalogo.SimCatPerfil
import com.sim.regional.SimSucursal

class RsEmpleado {
	
	RsConfEmpresa rsConfEmpresa
	
	static belongsTo = [puesto : SimCatPuesto, perfil : SimCatPerfil, sucursalPertenece :  SimSucursal ]//, User

    static constraints = {
		puesto(nullable: true)
		perfil(nullable: true)
		sucursalPertenece(nullable: true)
		rsConfEmpresa(nullable: false)
    }
}
