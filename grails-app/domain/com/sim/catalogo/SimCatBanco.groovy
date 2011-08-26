package com.sim.catalogo

import com.sim.empresa.RsCuentaBancaria

class SimCatBanco {

	String  claveBanco
	String  nombreBanco
	
	static hasMany = [cuentasBancarias :RsCuentaBancaria] 

    static constraints = {
		claveBanco(size:5..15, unique: true, nullable: false, blank: false)
		nombreBanco(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${nombreBanco}"
	}
}
