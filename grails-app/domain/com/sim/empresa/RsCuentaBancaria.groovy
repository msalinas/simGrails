package com.sim.empresa

import com.sim.catalogo.SimCatBanco

class RsCuentaBancaria {
	
	String numeroDeCuenta
	String clabe
	
	static belongsTo = [banco : SimCatBanco, cliente : RsCliente]
	
    static constraints = {
		numeroDeCuenta size:5..25, blank: false, unique: false
		clabe          size:5..25, blank: false, unique: true
		banco()
		cliente()
    }
	
	String toString() {
		"${banco.nombreBanco}: ${numeroDeCuenta}}"
	}
}
