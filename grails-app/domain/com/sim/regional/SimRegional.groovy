package com.sim.regional

import com.sim.empresa.RsConfEmpresa
import com.sim.catalogo.SimCatTipoIdentificador
import com.rs.gral.RsGralTelefono

class SimRegional {
	
	String  claveRegional
	String  nombreRegional
	String  gerente
	String  coordinador
	RsConfEmpresa rsConfEmpresa
	SimCatTipoIdentificador claveIdentificador
	
	static hasMany = [ telefono : RsGralTelefono, sucursal : SimSucursal ]
	
    static constraints = {
		claveRegional(size:5..15, unique: true, nullable: false, blank: false)
		nombreRegional(size:5..50, unique: true, nullable: false, blank: false)
		gerente()
		coordinador()
		telefono()
		sucursal()
		claveIdentificador(validator: { cveIdentificador ->
			cveIdentificador.claveTipoIdentificador.equals "REGIONAL" })
		rsConfEmpresa(nullable: false)
    }
	
	String toString() {
		"REGIONAL: ${nombreRegional}"
	}
}
