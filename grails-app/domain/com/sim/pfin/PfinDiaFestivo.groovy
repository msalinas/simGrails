package com.sim.pfin

class PfinDiaFestivo {

	Date    diaFestivo
	String  descripcionDia

    static constraints = {
		diaFestivo(unique: true)
		descripcionDia(size:5..50, unique: true, nullable: false, blank: false)
    }

	String toString() {
		"${descripcionDia}"
	}
}
