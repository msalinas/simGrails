package com.sim.pfin

import com.sim.empresa.RsConfEmpresa

class PfinDiaFestivo {

	Date    diaFestivo
	String  descripcionDia
	RsConfEmpresa rsConfEmpresa

    static constraints = {
		diaFestivo(unique: true)
		descripcionDia(size:5..50, unique: true, nullable: false, blank: false)
		rsConfEmpresa(nullable: false)
    }


	String toString() {
		"Dia Festivo: ${descripcionDia}"
	}
}
