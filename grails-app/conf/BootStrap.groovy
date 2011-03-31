import com.sim.empresa.*
import com.sim.catalogo.*
import com.sim.pfin.*

class BootStrap {

    def init = { servletContext ->
		new RsConfGpoEmpresa(claveGrupoEmpresa: 'SIM',
							nombreGrupoEmpresa: 'SIM CREDITOS',
							fechaCreacion: new Date('01/01/2011')).save()

		new RsConfEmpresa(claveEmpresa: 'CREDITOS',
							nombreEmpresa: 'MICROFINANCIERA AZUL',
							fechaCreacion: new Date('01/01/2011'),
							rsConfGpoEmpresa: RsConfGpoEmpresa.findByClaveGrupoEmpresa('SIM')).save()

		new SimCatTipoAccesorio(claveTipoAccesorio: 'INTERES',
								nombreTipoAccesorio: 'INTERESES',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoAccesorio(claveTipoAccesorio: 'RECARGO',
								nombreTipoAccesorio: 'RECARGOS',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoAccesorio(claveTipoAccesorio: 'CARGO_COMISION',
								nombreTipoAccesorio: 'CARGO Y COMISIONES',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatAccesorio(claveAccesorio: 'CLAVE_6',
								nombreAccesorio: 'SEGURO DE VIDA',
								simCatTipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
								tasaIva : 0,
								beneficiario : 'true',
								accesorio : 'true',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatAccesorio(claveAccesorio: 'CLAVE_8',
								nombreAccesorio: 'SEGURO DEUDOR',
								simCatTipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
								tasaIva : 0,
								beneficiario : 'false',
								accesorio : 'true',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatAccesorio(claveAccesorio: 'CLAVE_8',
								nombreAccesorio: 'SEGURO DEUDOR',
								simCatTipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
								tasaIva : 0,
								beneficiario : 'false',
								accesorio : 'true',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatAccesorio(claveAccesorio: 'CLAVE_15',
								nombreAccesorio: 'ADMINISTRACION CREDITICIA',
								simCatTipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
								tasaIva : 0,
								beneficiario : 'false',
								accesorio : 'true',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatBanco(claveBanco: 'CLAVE_1',
								nombreBanco: 'BANCOMER',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatBanco(claveBanco: 'CLAVE_2',
								nombreBanco: 'BANAMEX',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE_1',
								nombreDescripcionTelefono: 'CASA',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE_2',
								nombreDescripcionTelefono: 'OFICINA',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new PfinDiaFestivo(diaFestivo:  new Date('01/01/2011'),
								descripcionDia: 'AÑO NUEVO',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoDocumento(claveTipoDocumentacion:  'CLAVE_1',
								nombreTipoDocumentacion: 'IDENTIFICACION OFICIAL',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoDocumento(claveTipoDocumentacion:  'CLAVE_2',
								nombreTipoDocumentacion: 'COMPROBANTE DE DOMICILIO',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoDocumento(claveTipoDocumentacion:  'CLAVE_3',
								nombreTipoDocumentacion: 'LEGAL',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatReporte(claveReporte:  'CLAVE_1',
								nombreReporte: 'ANEXO A',
								descripcion: 'DESCRIPCION DEL ANEXO A',
								aplicaA : 'Individual',
								nombreFuncion: 'SimReporteAnexoA',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatReporte(claveReporte:  'CLAVE_4',
								nombreReporte: 'PAGARE SOLIDARIO',
								descripcion: 'DESCRIPCION DEL PAGARE SOLIDARIO',
								aplicaA : 'Grupo',
								nombreFuncion: 'SimReportePagareSolidario',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatDocumento(claveDocumento:  'CLAVE_7',
								nombreDocumento: 'SOLICITUD',
								descripcion: 'FORMA PARA INCRESAR UN CRÉDITO',
								simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('CLAVE_1'),
								simCatReporte : '',
								esReporte : 'false',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatDocumento(claveDocumento:  'CLAVE_22',
								nombreDocumento: 'ANEXO A',
								descripcion: 'FORMATO ANEXO A',
								simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('CLAVE_3'),
								simCatReporte : SimCatReporte.findByClaveReporte('CLAVE_1'),
								esReporte : 'true',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatEscolaridad(claveEscolaridad:  'CLAVE_1',
								nombreEscolaridad: 'PRIMARIA',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
	
		new SimCatEscolaridad(claveEscolaridad:  'CLAVE_2',
								nombreEscolaridad: 'SECUNDARIA',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
	
									
    }

    def destroy = {
    }

}
