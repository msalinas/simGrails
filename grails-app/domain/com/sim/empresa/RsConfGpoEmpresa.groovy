package com.sim.empresa

class RsConfGpoEmpresa {

	String claveGrupoEmpresa
	String nombreGrupoEmpresa
	Date   fechaCreacion

    static constraints = {
		claveGrupoEmpresa(size:3..20, unique: true, nullable: false, blank: false)
		nombreGrupoEmpresa(size: 5..20, nullable: false, blank:false, validator: { nomGrupoEmpresa, rsConfGpoEmpresa ->
			nomGrupoEmpresa != rsConfGpoEmpresa.claveGrupoEmpresa}) //EJEMPLO PARA QUE EL VALOR DE claveGrupoEmpresa Y nombreGrupoEmpresa SEAN DIFERENTES
		fechaCreacion()
    }

	static hasMany = [ empresas : RsConfEmpresa ]

	String toString() {
		"${nombreGrupoEmpresa}"
	}


}
