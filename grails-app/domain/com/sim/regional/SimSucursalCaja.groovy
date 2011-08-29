package com.sim.regional

class SimSucursalCaja implements Comparable {

	String  claveCaja
	String  nombreCaja
	
    static constraints = {
		claveCaja(size:2..15, unique: true, nullable: false, blank: false)
		nombreCaja(size:3..50, unique: true, nullable: false, blank: false)
    }
	
	static belongsTo = [sucursal : SimSucursal]

	String toString() {
		"${claveCaja} - ${nombreCaja}"
	}

	//ORDENA CAJAS
	int compareTo(obj) {
		claveCaja.compareTo(obj.claveCaja)
	}
	static mapping = {
		sort "claveCaja"
	}
	
}
