import com.sun.org.apache.xalan.internal.xsltc.compiler.Import;

import com.sim.empresa.*
import com.sim.catalogo.*
import com.sim.pfin.*
import com.sim.usuario.*
import com.sim.regional.*
import com.rs.gral.*
import com.sim.prueba.*

class BootStrap {

	def springSecurityService

	def init = { servletContext ->

		def samples = [
					'asalazar' : [ fullName: 'ARTURO SALAZAR', email: "asalazar@example.org", apellidoPaterno: "SALAZAR", apellidoMaterno:"CASTAÑEDA" , primerNombre:"ARTURO" ],
					'mrugerio' : [ fullName: 'MIGUEL RUGERIO', email: "mrugerio@example.org", apellidoPaterno: "RUGERIO", apellidoMaterno:"FLORES" , primerNombre:"MIGUEL", segundoNombre:"ANGEL" ],
					'egarcia' : [ fullName: 'EFREN GARCIA', email: "egarcia@example.org", apellidoPaterno: "GARCIA", apellidoMaterno:"GUARNEROS" , primerNombre:"EFREN" ],
					'cgarcia' : [ fullName: 'CHRISTIAN GARCIA', email: "cgarcia@example.org", apellidoPaterno: "GARCIA", apellidoMaterno:"GARCIA" , primerNombre:"CHRISTIAN" ],
					'msalinas' : [fullName : 'MINERVA SALINAS', email: "msalinas@somewhere.net", apellidoPaterno: "SALINAS", apellidoMaterno:"MONTES" , primerNombre:"MINERVA" ],
					]
		
		def userRole = getOrCreateRole("ROLE_USER")
		def adminRole = getOrCreateRole("ROLE_ADMIN")

		def users = User.list() ?: []
		if (!users) {
			// Start with the admin user.
			def adminUser = new User(
					username: "admin",
						apellidoPaterno: "ADMINISTRADOR",
						primerNombre: "MICROFINANCIERAS",
						email : "mikerugerio@gmail.com",
					password: springSecurityService.encodePassword("4321"),
					enabled: true).save()
					
			SecUserSecRole.create adminUser, adminRole

			// Now the normal users.
			samples.each { username, profileAttrs ->
				def user = new User(
						username: username,
						apellidoPaterno: profileAttrs.apellidoPaterno,
						apellidoMaterno: profileAttrs.apellidoMaterno,
						primerNombre: profileAttrs.primerNombre,
						segundoNombre: profileAttrs.segundoNombre,
						email: profileAttrs.email,
						password: springSecurityService.encodePassword("1234"),
						enabled: true)
				if (user.validate()) {
					println "Creando usuario: ${profileAttrs.fullName}..."

					user.save(flush:true)

					def rel = SecUserSecRole.create(user, userRole)
					if (rel.hasErrors()) println "Failed to assign user role to ${user.username}: ${rel.errors}"

					users << user
				}
				else {
					println("\n\n\nError in account bootstrap for ${username}!\n\n\n")
					user.errors.each {err -> println err }
				}
			}
		}


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

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE1',
				nombreDescripcionTelefono: 'CASA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE2',
				nombreDescripcionTelefono: 'OFICINA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE3',
				nombreDescripcionTelefono: 'FAX',
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

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'CLAVE_1',
				nombreEtapaPrestamo: 'SOLICITADO',
				descripcionEtapaPrestamo: 'SOLICITADO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'CLAVE_2',
				nombreEtapaPrestamo: 'BURO DE CREDITO',
				descripcionEtapaPrestamo: 'BURO DE CREDITO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatFondeador(claveFondeador:  'CLAVE_1',
				nombreFondeador: 'PRONAFIN',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatLineaFondeo(numeroLinea:  'LINEA 1',
				monto: 5000,
				montoDisponible: 5000,
				tasa: 15,
				fechaInicio: new Date('01/01/2011'),
				fechaVigencia : new Date('01/01/2015'),
				simCatFondeador : SimCatFondeador.findByClaveFondeador('CLAVE_1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatLineaFondeo(numeroLinea:  'LINEA 2',
				monto: 25000,
				montoDisponible: 5000,
				tasa: 10,
				fechaInicio: new Date('01/01/2011'),
				fechaVigencia : new Date('01/01/2019'),
				simCatFondeador : SimCatFondeador.findByClaveFondeador('CLAVE_1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()


		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_01',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL E INTERES',
				descripcionMetodoCalculo: 'INTERES GLOBAL',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_02',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'INTERES SOBRE SALDO INSOLUTO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_05',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'SIN RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_06',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO RI',
				descripcionMetodoCalculo: 'RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatParentesco(claveParentesco:  'MADRE',
				nombreParentesco: 'MADRE',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatParentesco(claveParentesco:  'PADRE',
				nombreParentesco: 'PADRE',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatPerfil(clavePerfil:  'CAJERO',
				nombrePerfil: 'CAJERO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatPerfil(clavePerfil:  'EJECRE',
				nombrePerfil: 'EJECUTIVO DE CREDITO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatPeriodicidad(clavePeriodicidad:  'CLAVE_1',
				nombrePeriodicidad: 'ANUAL',
				cantidadPagos: 1,
				numeroDias: 360,
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatPeriodicidad(clavePeriodicidad:  'CLAVE_2',
				nombrePeriodicidad: 'MENSUAL',
				cantidadPagos: 12,
				numeroDias: 30,
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatPuesto(clavePuesto:  'DirGen',
				nombrePuesto: 'DIRECTOR GENERAL',
				descripcionPuesto: 'DIRECTOR GENERAL MICRO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatPuesto(clavePuesto:  'GerRie',
				nombrePuesto: 'GERENTE DE RIESGOS',
				descripcionPuesto: 'GERENTE DE RIESGOS',
				dependeDe : SimCatPuesto.findByClavePuesto('DirGen'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatPuesto(clavePuesto:  'CooRie',
				nombrePuesto: 'COORDINADOR DE RIESGOS',
				descripcionPuesto: 'COORDINADOR DE RIESGOS',
				dependeDe : SimCatPuesto.findByClavePuesto('GerRie'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatRechazoComite(claveRechazoComite:  '101',
				nombreRechazoComite: 'FALTA DE DOCUMENTOS',
				descripcionRechazoComite: 'FALTA DE DOCUMENTOS',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatRechazoComite(claveRechazoComite:  '102',
				nombreRechazoComite: 'ANTECEDENTES PENALES ',
				descripcionRechazoComite: 'ANTECEDENTES PENALES ',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'CIUDAD',
				nombreTipoAsentamiento: 'CIUDAD',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'COLONIA',
				nombreTipoAsentamiento: 'COLONIA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'URBANA',
				nombreTipoAsentamiento: 'URBANA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoDomicilio(claveTipoDomicilio:  'CLAVE1',
				nombreTipoDomicilio: 'PROPIA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoDomicilio(claveTipoDomicilio:  'CLAVE2',
				nombreTipoDomicilio: 'RENTADA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoGarantia(claveTipoGarantia:  'CLAVE1',
				nombreTipoGarantia: 'PRENDARIA',
				descripcionTipoGarantia: 'DESCRIPCION PRENDARIA',
				requisitosTipoGarantia: 'REQUISITOS PRENDARIA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoGarantia(claveTipoGarantia:  'CLAVE2',
				nombreTipoGarantia: 'QUIFOGRAFARIA',
				descripcionTipoGarantia: 'DESCRIPCION QUIFOGRAFARIA',
				requisitosTipoGarantia: 'REQUISITOS QUIFOGRAFARIA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'AVAL',
				nombreTipoIdentificador: 'AVAL O FIADOR',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'CLIENTE',
				nombreTipoIdentificador: 'CLIENTE',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'REGIONAL',
				nombreTipoIdentificador: 'REGIONAL',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'SUCURSAL',
				nombreTipoIdentificador: 'SUCURSAL',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE1',
				nombreTipoNegocio: 'FIJO',
				descripcionTipoNegocio: 'DESCRIPCION FIJO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE2',
				nombreTipoNegocio: 'SEMIFIJO',
				descripcionTipoNegocio: 'DESCRIPCION SEMIFIJO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoPersona(claveTipoPersona:  'AVAL',
				nombreTipoPersona: 'AVAL',
				descripcionTipoPersona: 'DESCRIPCION AVAL',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTipoPersona(claveTipoPersona:  'CLIENTE',
				nombreTipoPersona: 'CLIENTE',
				descripcionTipoPersona: 'DESCRIPCION CLIENTE',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatVerificacionReferencia(claveTipoReferencia:  'CLAVE1',
				nombreTipoReferencia: 'REPUTACIÓN DE BUEN TRABAJADOR',
				descripcionTipoReferencia: 'REPUTACIÓN DE BUEN TRABAJADOR',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()


		new SimCatVerificacionReferencia(claveTipoReferencia:  'CLAVE4',
				nombreTipoReferencia: 'LO CONOCEN PERO NO SABEN SU CALIDAD COMO PERSONA',
				descripcionTipoReferencia: 'LO CONOCEN PERO NO SABEN SU CALIDAD COMO PERSONA',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTasaReferencia(claveTasaReferencia:  'CLAVE1',
				nombreTasaReferencia: 'CETES 28',
				descripcionTasaReferencia: 'DESCRIPCION CETES 28',
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTasaReferencia(claveTasaReferencia:  'CLAVE2',
				nombreTasaReferencia: 'BONO',
				descripcionTasaReferencia: 'DESCRIPCION BONO',
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_2'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTasaPapel(claveTasaPapel:  'CLAVE1',
				fechaPublicacion: new Date('01/01/2011'),
				valorTasaPapel: 5.85,
				tasaReferencia: SimCatTasaReferencia.findByClaveTasaReferencia('CLAVE2'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimCatTasaPapel(claveTasaPapel:  'CLAVE2',
				fechaPublicacion: new Date('01/05/2011'),
				valorTasaPapel: 8.85,
				tasaReferencia: SimCatTasaReferencia.findByClaveTasaReferencia('CLAVE2'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimRegional(claveRegional:  'REGION1',
				nombreRegional: 'TOLUCA',
				gerente: 'LUIS',
				coordinador: 'ALBERTO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimRegional(claveRegional:  'REGION2',
				nombreRegional: 'PUEBLA',
				gerente: 'ARMANDO',
				coordinador: 'GILBERTO',
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimSucursal(claveSucursal:  'SUCURSAL1',
				nombreSucursal: 'SAN MATEO',
				gerente: 'ARMANDO',
				coordinador: 'GILBERTO',
				regional : SimRegional.findByClaveRegional('REGION1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimSucursal(claveSucursal:  'SUCURSAL2',
				nombreSucursal: 'SANTIAGO TIANGUISTENCO',
				gerente: 'MARIO',
				coordinador: 'GUSTAVO',
				regional : SimRegional.findByClaveRegional('REGION1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimSucursal(claveSucursal:  'SUCURSAL3',
				nombreSucursal: 'IXTAPAN DE LA SAL',
				gerente: 'MARIO',
				coordinador: 'GUSTAVO',
				regional : SimRegional.findByClaveRegional('REGION1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new RsGralTelefono(telefono:  '111111111',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new RsGralTelefono(telefono:  '222222222',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE3'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new RsGralTelefono(telefono:  '33333333',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE1'),
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new RsGralTelefono(telefono:  '444444444',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()



		new Country(nameCountry: 'MEXICO',
				abbr: 'MEX',
				language: 'ESPANOL').save()

		new Country(nameCountry: 'ESTADOS UNIDOS',
				abbr: 'USA',
				language: 'INGLES').save()

		new City(nameCity: 'DISTRITO FEDERAL',
				timezone: 'DF',
				country : Country.findByNameCountry('MEXICO')).save()

		new City(nameCity: 'ESTADO MEXICO',
				timezone: 'TOLUCA',
				country : Country.findByNameCountry('MEXICO')).save()

		new City(nameCity: 'LOS ANGELES',
				timezone: 'LA',
				country : Country.findByNameCountry('ESTADOS UNIDOS')).save()

		new City(nameCity: 'NEW YORK',
				timezone: 'NY',
				country : Country.findByNameCountry('ESTADOS UNIDOS')).save()

		new RsGralEstado(cveEstado: 'AGS',
				nombreEstado: 'AGUASCALIENTES',
				aliasEstado : 'AGS').save()

		new RsGralEstado(cveEstado: 'DF',
				nombreEstado: 'DISTRITO FEDERAL',
				aliasEstado : 'DF').save()

		new RsGralEstado(cveEstado: 'EDOMEX',
				nombreEstado: 'ESTADO DE MEXICO',
				aliasEstado : 'EDOMEX').save()

		new RsGralCiudad(nombreCiudad: 'EL COLORADO',
				estado : RsGralEstado.findByCveEstado('AGS')).save()

		new RsGralCiudad(nombreCiudad: 'AMAPOLAS DEL RIO',
				estado : RsGralEstado.findByCveEstado('AGS')).save()

		new RsGralCiudad(nombreCiudad: 'EL CONEJAL',
				estado : RsGralEstado.findByCveEstado('AGS')).save()

		new RsGralCiudad(nombreCiudad: 'DISTRITO NORTE',
				estado : RsGralEstado.findByCveEstado('DF')).save()

		new RsGralCiudad(nombreCiudad: 'DISTRITO SUR',
				estado : RsGralEstado.findByCveEstado('DF')).save()

		new RsGralCiudad(nombreCiudad: 'METEPEC',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save()

		new RsGralCiudad(nombreCiudad: 'CHIMALHUACAN',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save()

		new RsGralCiudad(nombreCiudad: 'MEXICO',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CUAUHTEMOC',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'VENUSTIANO CARRANZA',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'ALVARO OBREGON',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'BENITO JUAREZ',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'COYOACAN',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'TLALPAN',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save()


		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save()

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO UNO',
				codigoPostal: '01000',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO DOS',
				codigoPostal: '01010',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO TRES',
				codigoPostal: '01020',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO UNO',
				codigoPostal: '01100',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO DOS',
				codigoPostal: '01110',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO TRES',
				codigoPostal: '01120',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO UNO',
				codigoPostal: '01200',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO DOS',
				codigoPostal: '01210',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO TRES',
				codigoPostal: '01220',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO UNO',
				codigoPostal: '01230',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO DOS',
				codigoPostal: '01240',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO TRES',
				codigoPostal: '01250',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO UNO',
				codigoPostal: '01260',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO DOS',
				codigoPostal: '01270',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO TRES',
				codigoPostal: '01280',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO UNO',
				codigoPostal: '01290',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO DOS',
				codigoPostal: '01300',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO TRES',
				codigoPostal: '01310',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO UNO',
				codigoPostal: '01320',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO DOS',
				codigoPostal: '01330',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO TRES',
				codigoPostal: '01340',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO UNO',
				codigoPostal: '01350',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO DOS',
				codigoPostal: '01360',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO TRES',
				codigoPostal: '01370',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO UNO',
				codigoPostal: '01380',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO DOS',
				codigoPostal: '01390',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO TRES',
				codigoPostal: '01400',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'VISTA ALEGRE',
				codigoPostal: '06860',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'BUENOS AIRES',
				codigoPostal: '06861',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'BOTURINI',
				codigoPostal: '06862',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA UNO',
				codigoPostal: '06900',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA DOS',
				codigoPostal: '06910',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA TRES',
				codigoPostal: '06920',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON UNO',
				codigoPostal: '01410',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON DOS',
				codigoPostal: '01420',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON TRES',
				codigoPostal: '01430',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'SANTA CRUZ ATOYAC',
				codigoPostal: '01440',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'DEL VALLE',
				codigoPostal: '01450',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'NARVARTE',
				codigoPostal: '01460',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ UNO',
				codigoPostal: '01470',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ DOS',
				codigoPostal: '01480',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ TRES',
				codigoPostal: '01490',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN UNO',
				codigoPostal: '01500',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN DOS',
				codigoPostal: '01510',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN TRES',
				codigoPostal: '01520',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO UNO',
				codigoPostal: '01530',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO DOS',
				codigoPostal: '01540',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO TRES',
				codigoPostal: '01550',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO UNO',
				codigoPostal: '01560',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO DOS',
				codigoPostal: '01570',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO TRES',
				codigoPostal: '01580',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO UNO',
				codigoPostal: '01590',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO DOS',
				codigoPostal: '01600',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO TRES',
				codigoPostal: '01610',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO UNO',
				codigoPostal: '01620',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO DOS',
				codigoPostal: '01630',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO TRES',
				codigoPostal: '01640',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO UNO',
				codigoPostal: '01650',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO DOS',
				codigoPostal: '01660',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO TRES',
				codigoPostal: '01670',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO UNO',
				codigoPostal: '01680',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO DOS',
				codigoPostal: '01690',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO TRES',
				codigoPostal: '01700',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()



		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO UNO',
				codigoPostal: '01710',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO DOS',
				codigoPostal: '01720',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO TRES',
				codigoPostal: '01730',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO UNO',
				codigoPostal: '01740',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO DOS',
				codigoPostal: '01750',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO TRES',
				codigoPostal: '01760',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO UNO',
				codigoPostal: '01770',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()


		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO DOS',
				codigoPostal: '01780',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO TRES',
				codigoPostal: '01790',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save()

		new RsGralDomicilio(calle: 'Las Palmas',
				numeroInterior: '4',
				numeroExterior: '67',
				esFiscal: 'true',
				comentarios : 'ENFRENTE DE UNA FARMACIA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06860'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		//IMPLEMENTACION DE SEGURIDAD A NIVEL Dynamic request maps
		new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save()
		new Requestmap(url: '/rsConfGpoEmpresa/**', configAttribute: 'ROLE_USER').save()
		new Requestmap(url: '/rsConfEmpresa/create', configAttribute: 'ROLE_USER').save()
	}

	def destroy = {
	}

	private getOrCreateRole(name) {
		def role = SecRole.findByAuthority(name)
		if (!role) role = new SecRole(authority: name).save()
		if (!role)  println "Unable to save role ${name}"
		return role
	}
}
