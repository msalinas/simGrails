import com.sun.org.apache.xalan.internal.xsltc.compiler.Import;

import com.sim.empresa.*
import com.sim.catalogo.*
import com.sim.pfin.*
import com.sim.usuario.*
import com.sim.regional.*
import com.sim.producto.*
import com.rs.gral.*
import com.sim.prueba.*

class BootStrap {

	def springSecurityService

	def init = { servletContext ->

		new RsConfGpoEmpresa(claveGrupoEmpresa: 'SIM',
				nombreGrupoEmpresa: 'SIM CREDITOS',
				fechaCreacion: new Date('01/01/2011')).save()

		new RsConfEmpresa(claveEmpresa: 'CREDITOS',
				nombreEmpresa: 'MICROFINANCIERA AZUL',
				fechaCreacion: new Date('01/01/2011'),
				grupoEmpresa: RsConfGpoEmpresa.findByClaveGrupoEmpresa('SIM')).save()

		//ARREGLO PARA CREAR USUARIOS
		def samples = [
					'asalazar' : [ fullName: 'ARTURO SALAZAR', email: "asalazar@example.org", apellidoPaterno: "SALAZAR", apellidoMaterno:"CASTAÑEDA" , primerNombre:"ARTURO" ],
					'mrugerio' : [ fullName: 'MIGUEL RUGERIO', email: "mrugerio@gmail.com", apellidoPaterno: "RUGERIO", apellidoMaterno:"FLORES" , primerNombre:"MIGUEL", segundoNombre:"ANGEL" ],
					'egarcia' : [ fullName: 'EFREN GARCIA', email: "egarcia@example.org", apellidoPaterno: "GARCIA", apellidoMaterno:"GUARNEROS" , primerNombre:"EFREN" ],
					'cgarcia' : [ fullName: 'CHRISTIAN GARCIA', email: "cgarcia@example.org", apellidoPaterno: "GARCIA", apellidoMaterno:"GARCIA" , primerNombre:"CHRISTIAN" ],
					'msalinas' : [fullName : 'MINERVA SALINAS', email: "msalinas@somewhere.net", apellidoPaterno: "SALINAS", apellidoMaterno:"MONTES" , primerNombre:"MINERVA" ],
				]

		def userRole = getOrCreateRole("ROLE_USER")
		def adminRole = getOrCreateRole("ROLE_ADMIN")

		def users = Usuario.list() ?: []

		if (!users) {
			// DA DE ALTA AL USUARIO ADMINISTRADOR
			def adminUser = new Usuario(
					username: "admin",
					password: springSecurityService.encodePassword("4321"),
					enabled: true).save()

			//ASIGNA LOS ROLES AL USUARIO ADMINISTRADOR
			SecUserSecRole.create adminUser, adminRole

			//DA DE ALTA UNA PERSONA Y LE ASIGNA EL USUARIO ADMINISTRADOR
			def adminPersona = new RsPersona(
					apellidoPaterno: "ADMINISTRADOR",
					primerNombre: "MICROFINANCIERAS",
					email : "mikerugerio@gmail.com",
					usuario : adminUser).save()

			// DA DE ALTA A LOS USUARIOS DEL ARREGLO DEFINIDO ANTERIORMENTE
			samples.each { username, profileAttrs ->
				def user = new Usuario(
						username: username,
						password: springSecurityService.encodePassword("1234"),
						enabled: true)
				if (user.validate()) {
					println "Creando usuario: ${username}..."
					//SE SALVA AL USUARIO
					user.save(flush:true)

					def rel = SecUserSecRole.create(user, userRole)
					if (rel.hasErrors()) println "Failed to assign user role to ${user.username}: ${rel.errors}"

					users << user

					//SALVA A LA PERSONA Y LE ASIGNA EL USUARIO CREADO
					def persona = new RsPersona(
							apellidoPaterno: profileAttrs.apellidoPaterno,
							apellidoMaterno: profileAttrs.apellidoMaterno,
							primerNombre: profileAttrs.primerNombre,
							segundoNombre: profileAttrs.segundoNombre,
							email: profileAttrs.email,
							usuario : user)
					if (persona.validate()){
						println "Creando persona ${profileAttrs.fullName} del usuario ${username}..."
						persona.save(flush:true)

					}else{
						println("\n\n\nError en crear la persona del usuario ${username}!\n\n\n")
						persona.errors.each {err -> println err }
					}
				}
				else {
					println("\n\n\nError in account bootstrap for ${username}!\n\n\n")
					user.errors.each {err -> println err }
				}
			}
		}



		new SimCatTipoAccesorio(claveTipoAccesorio: 'INTERES',
				nombreTipoAccesorio: 'INTERESES',
				).save()

		new SimCatTipoAccesorio(claveTipoAccesorio: 'RECARGO',
				nombreTipoAccesorio: 'RECARGOS',
				).save()

		new SimCatTipoAccesorio(claveTipoAccesorio: 'CARGO_COMISION',
				nombreTipoAccesorio: 'CARGO Y COMISIONES',
				).save()

		new SimCatAccesorio(claveAccesorio: 'CLAVE_6',
				nombreAccesorio: 'SEGURO DE VIDA',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
				tasaIva : 0,
				beneficiario : 'true',
				).save()

		new SimCatAccesorio(claveAccesorio: 'CLAVE_8',
				nombreAccesorio: 'SEGURO DEUDOR',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
				tasaIva : 0,
				beneficiario : 'false',
				).save()

		new SimCatAccesorio(claveAccesorio: 'CLAVE_15',
				nombreAccesorio: 'ADMINISTRACION CREDITICIA',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
				tasaIva : 0,
				beneficiario : 'false',
				).save()

		new SimCatBanco(claveBanco: 'BANCOMER',
				nombreBanco: 'BANCOMER',
				).save()

		new SimCatBanco(claveBanco: 'BANAMEX',
				nombreBanco: 'BANAMEX',
				).save()

		new SimCatFormaEntrega(claveFormaEntrega: 'CAJA',
				nombreFormaEntrega: 'DIRECTAMENTE EN CAJA',
				).save()

		new SimCatFormaEntrega(claveFormaEntrega: 'CHEQUE',
				nombreFormaEntrega: 'EN CHEQUE',
				).save()

		new SimCatFormaEntrega(claveFormaEntrega: 'ELECTRONICA',
				nombreFormaEntrega: 'TRANSFERENCIA ELECTRONICA',
				).save()

		new SimCatFormaEntrega(claveFormaEntrega: 'FONDOS',
				nombreFormaEntrega: 'DISPERCION DE FONDOS',
				).save()

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE1',
				nombreDescripcionTelefono: 'CASA',
				).save()

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE2',
				nombreDescripcionTelefono: 'OFICINA',
				).save()

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE3',
				nombreDescripcionTelefono: 'FAX',
				).save()

		new PfinDiaFestivo(diaFestivo:  new Date('01/01/2011'),
				descripcionDia: 'AÑO NUEVO',
				).save()

		new SimCatTipoDocumento(claveTipoDocumentacion:  'CLAVE_1',
				nombreTipoDocumentacion: 'IDENTIFICACION OFICIAL',
				).save()

		new SimCatTipoDocumento(claveTipoDocumentacion:  'CLAVE_2',
				nombreTipoDocumentacion: 'COMPROBANTE DE DOMICILIO',
				).save()

		new SimCatTipoDocumento(claveTipoDocumentacion:  'CLAVE_3',
				nombreTipoDocumentacion: 'LEGAL',
				).save()

		new SimCatReporte(claveReporte:  'CLAVE_1',
				nombreReporte: 'ANEXO A',
				descripcion: 'DESCRIPCION DEL ANEXO A',
				aplicaA : 'INDIVIDUAL',
				nombreFuncion: 'SimReporteAnexoA',
				).save()

		new SimCatReporte(claveReporte:  'CLAVE_4',
				nombreReporte: 'PAGARE SOLIDARIO',
				descripcion: 'DESCRIPCION DEL PAGARE SOLIDARIO',
				aplicaA : 'GRUPO',
				nombreFuncion: 'SimReportePagareSolidario',
				).save()

		new SimCatDocumento(claveDocumento:  'CLAVE7',
				nombreDocumento: 'SOLICITUD',
				descripcion: 'FORMA PARA INGRESAR UN CRÉDITO',
				simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('CLAVE_1'),
				esReporte : 'false',
				).save()

		new SimCatDocumento(claveDocumento:  'CLAVE22',
				nombreDocumento: 'ANEXO A',
				descripcion: 'FORMATO ANEXO A',
				simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('CLAVE_3'),
				simCatReporte : SimCatReporte.findByClaveReporte('CLAVE_1'),
				esReporte : 'true',
				).save()

		new SimCatDocumento(claveDocumento:  'CLAVE1',
				nombreDocumento: 'CREDENCIAL IFE',
				descripcion: 'CREDENCIAL IFE',
				simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('CLAVE_1'),
				simCatReporte : SimCatReporte.findByClaveReporte('CLAVE_4'),
				esReporte : 'false',
				).save()


		new SimCatEscolaridad(claveEscolaridad:  'CLAVE1',
				nombreEscolaridad: 'PRIMARIA',
				).save()

		new SimCatEscolaridad(claveEscolaridad:  'CLAVE2',
				nombreEscolaridad: 'SECUNDARIA',
				).save()

		new SimCatEscolaridad(claveEscolaridad:  'CLAVE3',
				nombreEscolaridad: 'SECUNDARIA',
				).save()

		new SimCatEscolaridad(claveEscolaridad:  'CLAVE4',
				nombreEscolaridad: 'LICENCIATURA',
				).save()

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'CLAVE_1',
				nombreEtapaPrestamo: 'SOLICITADO',
				descripcionEtapaPrestamo: 'SOLICITADO',
				).save()

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'CLAVE_2',
				nombreEtapaPrestamo: 'BURO DE CREDITO',
				descripcionEtapaPrestamo: 'BURO DE CREDITO',
				).save()

		new SimCatFondeador(claveFondeador:  'CLAVE_1',
				nombreFondeador: 'PRONAFIN',
				).save()

		new SimCatLineaFondeo(numeroLinea:  'LINEA 1',
				monto: 5000,
				montoDisponible: 5000,
				tasa: 15,
				fechaInicio: new Date('01/01/2011'),
				fechaVigencia : new Date('01/01/2015'),
				simCatFondeador : SimCatFondeador.findByClaveFondeador('CLAVE_1'),
				).save()

		new SimCatLineaFondeo(numeroLinea:  'LINEA 2',
				monto: 25000,
				montoDisponible: 5000,
				tasa: 10,
				fechaInicio: new Date('01/01/2011'),
				fechaVigencia : new Date('01/01/2019'),
				simCatFondeador : SimCatFondeador.findByClaveFondeador('CLAVE_1'),
				).save()


		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_01',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL E INTERES',
				descripcionMetodoCalculo: 'INTERES GLOBAL',
				).save()

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_02',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'INTERES SOBRE SALDO INSOLUTO',
				).save()

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_05',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'SIN RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				).save()

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_06',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO RI',
				descripcionMetodoCalculo: 'RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				).save()

		new SimCatParentesco(claveParentesco:  'MADRE',
				nombreParentesco: 'MADRE',
				).save()

		new SimCatParentesco(claveParentesco:  'PADRE',
				nombreParentesco: 'PADRE',
				).save()

		new SimCatPerfil(clavePerfil:  'CAJERO',
				nombrePerfil: 'CAJERO',
				).save()

		new SimCatPerfil(clavePerfil:  'EJECRE',
				nombrePerfil: 'EJECUTIVO DE CREDITO',
				).save()

		new SimCatPerfil(clavePerfil:  'GERENTE',
				nombrePerfil: 'GERENTE',
				).save()


		new SimCatPeriodicidad(clavePeriodicidad:  'CLAVE_1',
				nombrePeriodicidad: 'ANUAL',
				cantidadPagos: 1,
				numeroDias: 360,
				).save()

		new SimCatPeriodicidad(clavePeriodicidad:  'CLAVE_2',
				nombrePeriodicidad: 'MENSUAL',
				cantidadPagos: 12,
				numeroDias: 30,
				).save()

		new SimCatPuesto(clavePuesto:  'DirGen',
				nombrePuesto: 'DIRECTOR GENERAL',
				descripcionPuesto: 'DIRECTOR GENERAL MICRO',
				).save()

		new SimCatPuesto(clavePuesto:  'GerRie',
				nombrePuesto: 'GERENTE DE RIESGOS',
				descripcionPuesto: 'GERENTE DE RIESGOS',
				dependeDe : SimCatPuesto.findByClavePuesto('DirGen'),
				).save()

		new SimCatPuesto(clavePuesto:  'CooRie',
				nombrePuesto: 'COORDINADOR DE RIESGOS',
				descripcionPuesto: 'COORDINADOR DE RIESGOS',
				dependeDe : SimCatPuesto.findByClavePuesto('GerRie'),
				).save()

		new SimCatPuesto(clavePuesto:  'ASESORSUC',
				nombrePuesto: 'ASESOR DE SUCURSAL',
				descripcionPuesto: 'ASESOR DE SUCURSAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DirGen'),
				).save()

		new SimCatPuesto(clavePuesto:  'GERREG',
				nombrePuesto: 'GERENTE REGIONAL',
				descripcionPuesto: 'GERENTE DE REGIONAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DirGen'),
				).save()

		new SimCatPuesto(clavePuesto:  'COOREG',
				nombrePuesto: 'COORDINADOR REGIONAL',
				descripcionPuesto: 'COORDINADOR DE REGIONAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DirGen'),
				).save()


		new SimCatRechazoComite(claveRechazoComite:  '101',
				nombreRechazoComite: 'FALTA DE DOCUMENTOS',
				descripcionRechazoComite: 'FALTA DE DOCUMENTOS',
				).save()

		new SimCatRechazoComite(claveRechazoComite:  '102',
				nombreRechazoComite: 'ANTECEDENTES PENALES ',
				descripcionRechazoComite: 'ANTECEDENTES PENALES ',
				).save()

		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'CIUDAD',
				nombreTipoAsentamiento: 'CIUDAD',
				).save()
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'COLONIA',
				nombreTipoAsentamiento: 'COLONIA',
				).save()
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'URBANA',
				nombreTipoAsentamiento: 'URBANA',
				).save()

		new SimCatTipoDomicilio(claveTipoDomicilio:  'CLAVE1',
				nombreTipoDomicilio: 'PROPIA',
				).save()

		new SimCatTipoDomicilio(claveTipoDomicilio:  'CLAVE2',
				nombreTipoDomicilio: 'RENTADA',
				).save()

		new SimCatTipoGarantia(claveTipoGarantia:  'CLAVE1',
				nombreTipoGarantia: 'PRENDARIA',
				descripcionTipoGarantia: 'DESCRIPCION PRENDARIA',
				requisitosTipoGarantia: 'REQUISITOS PRENDARIA',
				).save()

		new SimCatTipoGarantia(claveTipoGarantia:  'CLAVE2',
				nombreTipoGarantia: 'QUIFOGRAFARIA',
				descripcionTipoGarantia: 'DESCRIPCION QUIFOGRAFARIA',
				requisitosTipoGarantia: 'REQUISITOS QUIFOGRAFARIA',
				).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'AVAL',
				nombreTipoIdentificador: 'AVAL O FIADOR',
				).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'CLIENTE',
				nombreTipoIdentificador: 'CLIENTE',
				).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'REGIONAL',
				nombreTipoIdentificador: 'REGIONAL',
				).save()

		new SimCatTipoIdentificador(claveTipoIdentificador:  'SUCURSAL',
				nombreTipoIdentificador: 'SUCURSAL',
				).save()

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE1',
				nombreTipoNegocio: 'FIJO',
				descripcionTipoNegocio: 'DESCRIPCION FIJO',
				).save()

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE2',
				nombreTipoNegocio: 'SEMIFIJO',
				descripcionTipoNegocio: 'DESCRIPCION SEMIFIJO',
				).save()

		new SimCatTipoPersona(claveTipoPersona:  'AVAL',
				nombreTipoPersona: 'AVAL',
				descripcionTipoPersona: 'DESCRIPCION AVAL',
				).save()

		new SimCatTipoPersona(claveTipoPersona:  'CLIENTE',
				nombreTipoPersona: 'CLIENTE',
				descripcionTipoPersona: 'DESCRIPCION CLIENTE',
				).save()

		new SimCatTipoPersona(claveTipoPersona:  'USUARIO',
				nombreTipoPersona: 'USUARIO',
				descripcionTipoPersona: 'USUARIO DEL SISTEMA',
				).save()

		new SimCatTipoPersona(claveTipoPersona:  'EMPLEADO',
				nombreTipoPersona: 'EMPLEADO',
				descripcionTipoPersona: 'EMPLEADO DE LA EMPRESA',
				).save()


		new SimCatVerificacionReferencia(claveTipoReferencia:  'CLAVE1',
				nombreTipoReferencia: 'REPUTACIÓN DE BUEN TRABAJADOR',
				descripcionTipoReferencia: 'REPUTACIÓN DE BUEN TRABAJADOR',
				).save()


		new SimCatVerificacionReferencia(claveTipoReferencia:  'CLAVE4',
				nombreTipoReferencia: 'LO CONOCEN PERO NO SABEN SU CALIDAD COMO PERSONA',
				descripcionTipoReferencia: 'LO CONOCEN PERO NO SABEN SU CALIDAD COMO PERSONA',
				).save()

		new SimCatTasaReferencia(claveTasaReferencia:  'CLAVE1',
				nombreTasaReferencia: 'CETES 28',
				descripcionTasaReferencia: 'DESCRIPCION CETES 28',
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1'),
				).save()

		new SimCatTasaReferencia(claveTasaReferencia:  'CLAVE2',
				nombreTasaReferencia: 'BONO',
				descripcionTasaReferencia: 'DESCRIPCION BONO',
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_2'),
				).save()

		new SimCatTasaPapel(claveTasaPapel:  'CLAVE1',
				fechaPublicacion: new Date('01/01/2011'),
				valorTasaPapel: 5.85,
				tasaReferencia: SimCatTasaReferencia.findByClaveTasaReferencia('CLAVE2'),
				).save()

		new SimCatTasaPapel(claveTasaPapel:  'CLAVE2',
				fechaPublicacion: new Date('01/05/2011'),
				valorTasaPapel: 8.85,
				tasaReferencia: SimCatTasaReferencia.findByClaveTasaReferencia('CLAVE2'),
				).save()

		new SimRegional(claveRegional:  'REGION1',
				nombreRegional: 'TOLUCA'
				).save()

		new SimRegional(claveRegional:  'REGION2',
				nombreRegional: 'PUEBLA'
				).save()

		new SimSucursal(claveSucursal:  'SUCURSAL1',
				nombreSucursal: 'SAN MATEO',
				gerente: 'ARMANDO',
				coordinador: 'GILBERTO',
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save()

		new SimSucursal(claveSucursal:  'SUCURSAL2',
				nombreSucursal: 'SANTIAGO TIANGUISTENCO',
				gerente: 'MARIO',
				coordinador: 'GUSTAVO',
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save()

		new SimSucursal(claveSucursal:  'SUCURSAL3',
				nombreSucursal: 'IXTAPAN DE LA SAL',
				gerente: 'MARIO',
				coordinador: 'GUSTAVO',
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save()

		new RsGralTelefono(telefono:  '111111111',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save()

		new RsGralTelefono(telefono:  '222222222',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE3'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save()

		new RsGralTelefono(telefono:  '33333333',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE1'),
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				).save()

		new RsGralTelefono(telefono:  '444444444',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				).save()

		new RsGralTelefono(telefono:  '555555555',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save()

		new RsGralTelefono(telefono:  '666666666',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE1'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save()


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
				).save()

		new RsGralDomicilio(calle: 'Direccion administrador',
				numeroInterior: '54',
				numeroExterior: '90',
				esFiscal: 'false',
				comentarios : 'CRUZANDO DE UNA AVENIDA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01790'),
				sucursal : SimSucursal.get(1),
				).save()

		new RsGralDomicilio(calle: 'Direccion administrador',
				numeroInterior: '78',
				numeroExterior: '905',
				esFiscal: 'true',
				comentarios : 'ATRAS CENTRO COMERCIAL',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01580'),
				persona : RsPersona.get(1),
				).save()

		new RsGralDomicilio(calle: 'BATALLONES ROJOS 205',
				numeroInterior: '504',
				numeroExterior: 'EDIF 8',
				esFiscal: 'true',
				comentarios : 'UNIDAD ALBARRADA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06862'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save()

		new RsGralDomicilio(calle: 'PROGRESISTA',
				numeroInterior: '202',
				numeroExterior: 'EDIF 6',
				esFiscal: 'false',
				comentarios : 'UNIDAD VICENTE',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01600'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save()

		//ASIGNA ATRIBUTOS A LA PERSONA mrugerio@gmail.com
		def personaMiguel = RsPersona.findByEmail('mrugerio@gmail.com')
		personaMiguel.sexo = "MASCULINO"
		personaMiguel.estadoCivil = "CASADO - BIENES MANCOMUNADOS"
		personaMiguel.fechaNacimiento = new Date('09/30/1974')
		personaMiguel.nombreAlterno = "MIKE RUGEIRO"
		personaMiguel.identificacionOficial = SimCatDocumento.findByClaveDocumento('CLAVE1')
		personaMiguel.numeroIdentificacionOficial = "RUFM727328328"
		personaMiguel.rfc = "RUFM89778"
		personaMiguel.curp = "RUMD76878968"
		personaMiguel.escolaridad  = SimCatEscolaridad.findByClaveEscolaridad('CLAVE4')
		personaMiguel.tiposPersona = [
			SimCatTipoPersona.findByClaveTipoPersona('USUARIO'),
			SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO')
		]
		personaMiguel.save()

		// DA DE ALTA UN USUARIO
		def userHector = new Usuario(
				username: "hreyes",
				password: springSecurityService.encodePassword("hreyes"),
				enabled: true).save()

		//ASIGNA LOS ROLES AL USUARIO ADMINISTRADOR
		SecUserSecRole.create userHector, adminRole

		//DA DE ALTA A LA PERSONA
		def personaHector = new RsPersona(
				apellidoPaterno: "REYES",
				apellidoMaterno: "ROMERO",
				primerNombre: "HECTOR",
				email: "hreyes@credi.com",
				usuario : userHector,
				sexo : "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('05/20/1978'),
				nombreAlterno : "HECTORIN",
				identificacionOficial : SimCatDocumento.findByClaveDocumento('CLAVE1'),
				numeroIdentificacionOficial : "RRHM727328328",
				rfc : "RRHM89778",
				curp : "RRHM76878968",
				escolaridad  : SimCatEscolaridad.findByClaveEscolaridad('CLAVE4'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('USUARIO'),
					SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO') ]
				).save()

		//DA DE ALTA A UN EMPLEADO
		def empleado = new RsEmpleado(
				persona : personaHector,
				puesto : SimCatPuesto.findByClavePuesto('COOREG'),
				perfil : SimCatPerfil.findByClavePerfil('EJECRE'),
				sucursalPertenece : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				fechaIngreso  : new Date('08/20/1999'),
				numeroNomina : "001",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL1'),
					SimSucursal.findByClaveSucursal('SUCURSAL2'),
					SimSucursal.findByClaveSucursal('SUCURSAL3')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION1'),
					SimRegional.findByClaveRegional('REGION2')]
				).save()

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  RsPersona.findByEmail('asalazar@example.org'),
				puesto : SimCatPuesto.findByClavePuesto('ASESORSUC'),
				perfil : SimCatPerfil.findByClavePerfil('EJECRE'),
				sucursalPertenece : SimSucursal.findByClaveSucursal('SUCURSAL2'),
				fechaIngreso  : new Date('08/20/2010'),
				numeroNomina : "002",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL1'),
					SimSucursal.findByClaveSucursal('SUCURSAL2'),
					SimSucursal.findByClaveSucursal('SUCURSAL3')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION1'),
					SimRegional.findByClaveRegional('REGION2')]
				).save()

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  RsPersona.findByEmail('cgarcia@example.org'),
				puesto : SimCatPuesto.findByClavePuesto('CooRie'),
				perfil : SimCatPerfil.findByClavePerfil('EJECRE'),
				sucursalPertenece : SimSucursal.findByClaveSucursal('SUCURSAL2'),
				fechaIngreso  : new Date('12/30/2005'),
				numeroNomina : "003",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL1')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION1')]
				).save()

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  RsPersona.findByEmail('egarcia@example.org'),
				puesto : SimCatPuesto.findByClavePuesto('GERREG'),
				perfil : SimCatPerfil.findByClavePerfil('GERENTE'),
				sucursalPertenece : SimSucursal.findByClaveSucursal('SUCURSAL2'),
				fechaIngreso  : new Date('12/05/2008'),
				numeroNomina : "004",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL2')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION1')]
				).save()

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  RsPersona.findByEmail('msalinas@somewhere.net'),
				puesto : SimCatPuesto.findByClavePuesto('GERREG'),
				perfil : SimCatPerfil.findByClavePerfil('GERENTE'),
				sucursalPertenece : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				fechaIngreso  : new Date('05/05/2009'),
				numeroNomina : "005",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL1')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION2')]
				).save()


		//DA DE ALTA UNA PERSONA CLIENTE
		def personaCliente = new RsPersona(
				apellidoPaterno: "NAVA",
				apellidoMaterno: "MARTINEZ",
				primerNombre: "ALEJANDRO",
				email: "alex@hotmail.com",
				sexo : "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('03/21/1973'),
				nombreAlterno : "EL MAESTRO",
				identificacionOficial : SimCatDocumento.findByClaveDocumento('CLAVE1'),
				numeroIdentificacionOficial : "NAMA3328328",
				rfc : "SDFF89778",
				curp : "SDFSDF6878968",
				escolaridad  : SimCatEscolaridad.findByClaveEscolaridad('CLAVE2'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')]
				).save()

		//DA DE ALTA UN CLIENTE
		def cliente = new RsCliente(
				persona :  personaCliente,
				ingresoSemanal : 4500.50,
				dependientesEconomicos : 4,
				destinoDelCredito : "AMPLIAR, ADECUAR O REPARAR EL LOCAL O VEHICULO",
				rolEnElHogar : "ESPOSO(A)",
				listaNegra : false).save()

		//DA DE ALTA CUENTA BANCARIA A UN CLIENTE
		def cuentaBancaria = new RsCuentaBancaria(
				numeroDeCuenta : "122222222",
				clabe : "222222222",
				banco :  SimCatBanco.findByClaveBanco('BANAMEX'),
				cliente : cliente).save()

		//DA DE ALTA CUENTA BANCARIA A UN CLIENTE
		cuentaBancaria = new RsCuentaBancaria(
				numeroDeCuenta : "111111111",
				clabe : "21111111",
				banco :  SimCatBanco.findByClaveBanco('BANCOMER'),
				cliente : cliente).save()

		//DA DE ALTA UN DOCUMENTO A UN CLIENTE
		def documentoCliente = new RsClienteDocumentacion(
				cliente: cliente,
				documento : SimCatDocumento.findByClaveDocumento('CLAVE7'),
				fechaRecibido : new Date('03/21/2011'),
				asesorVerifico : RsEmpleado.findByPersona(RsPersona.findByEmail('hreyes@credi.com')),
				documentacionCorrecta : true).save()

		//DA DE ALTA UN DOCUMENTO A UN CLIENTE
		documentoCliente = new RsClienteDocumentacion(
				cliente: cliente,
				documento : SimCatDocumento.findByClaveDocumento('CLAVE22'),
				fechaRecibido : new Date('12/29/2011'),
				asesorVerifico : RsEmpleado.findByPersona(RsPersona.findByEmail('asalazar@example.org')),
				documentacionCorrecta : true).save()

		//DA DE ALTA UN DOCUMENTO A UN PRODUCTO
		def producto = new SimProducto(
				claveProducto: "SOLIDARIO",
				nombreProducto: "SOLIDARIO",
				fechaInicioActivacion : new Date('08/01/2010'),
				fechaFinActivacion : new Date('08/01/2015'),
				aplicaA : "GRUPO",
				metodoCalculo : SimCatMetodoCalculo.findByClaveMetodoCalculo('CLAVE_05'),
				periodicidad : SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1'),
				montoMinimo :  14500.50,
				formaEntrega : SimCatFormaEntrega.findByClaveFormaEntrega('CAJA'),
				garantias : true,
				participantesCredito : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE'),
					SimCatTipoPersona.findByClaveTipoPersona('AVAL')
				],
				sucursales : [
					SimSucursal.findByClaveSucursal('SUCURSAL1'),
					SimSucursal.findByClaveSucursal('SUCURSAL2'),
				],
				asignarTodasSucursales : false).save()

		//ASIGNA UN GERENTE A LA REGION1
		def region = SimRegional.findByClaveRegional('REGION1')
		region.gerente = RsEmpleado.findByPersona(RsPersona.findByEmail('egarcia@example.org'))
		region.coordinador = RsEmpleado.findByPersona(RsPersona.findByEmail('hreyes@credi.com'))
		region.save()

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
