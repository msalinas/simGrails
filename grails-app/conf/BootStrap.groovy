import com.sun.org.apache.xalan.internal.xsltc.compiler.Import;

import com.sim.empresa.*
import com.sim.catalogo.*
import com.sim.cliente.*
import com.sim.pfin.*
import com.sim.usuario.*
import com.sim.regional.*
import com.sim.producto.*
import com.rs.gral.*
import com.sim.grupo.*
import com.sim.prueba.*

class BootStrap {

	def springSecurityService

	def init = { servletContext ->

		new SimCatTipoPersona(claveTipoPersona:  'AVAL',
				nombreTipoPersona: 'AVAL',
				descripcionTipoPersona: 'DESCRIPCION AVAL',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'CLIENTE',
				nombreTipoPersona: 'CLIENTE',
				descripcionTipoPersona: 'DESCRIPCION CLIENTE',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'USUARIO',
				nombreTipoPersona: 'USUARIO',
				descripcionTipoPersona: 'USUARIO DEL SISTEMA',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'EMPLEADO',
				nombreTipoPersona: 'EMPLEADO',
				descripcionTipoPersona: 'EMPLEADO DE LA EMPRESA',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'REFCLIENTE',
				nombreTipoPersona: 'REFERENCIA DEL CLIENTE',
				descripcionTipoPersona: 'REFERENCIA DEL CLIENTE',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'UEF',
				nombreTipoPersona: 'UEF DEL CLIENTE',
				descripcionTipoPersona: 'UNIDAD ECONOMICA FAMILIAR DEL CLIENTE',
				).save(failOnError: true)

		new SimCatTipoPersona(claveTipoPersona:  'GARDEP',
				nombreTipoPersona: 'GARANTE DEPOSITARIO',
				descripcionTipoPersona: 'GARANTE DEPOSITARIO DEL CLIENTE',
				).save(failOnError: true)

		new RsConfGpoEmpresa(claveGrupoEmpresa: 'SIM',
				nombreGrupoEmpresa: 'SIM CREDITOS',
				fechaCreacion: new Date('01/01/2011')).save(failOnError: true)

		new RsConfEmpresa(claveEmpresa: 'CREDITOS',
				nombreEmpresa: 'MICROFINANCIERA AZUL',
				fechaCreacion: new Date('01/01/2011'),
				grupoEmpresa: RsConfGpoEmpresa.findByClaveGrupoEmpresa('SIM')).save(failOnError: true)

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
					enabled: true).save(failOnError: true)

			//ASIGNA LOS ROLES AL USUARIO ADMINISTRADOR
			SecUserSecRole.create adminUser, adminRole

			//DA DE ALTA UNA PERSONA Y LE ASIGNA EL USUARIO ADMINISTRADOR
			def adminPersona = new RsPersona(
					apellidoPaterno: "ADMINISTRADOR",
					primerNombre: "MICROFINANCIERAS",
					email : "mikerugerio@gmail.com",
					tiposPersona : [
						SimCatTipoPersona.findByClaveTipoPersona('USUARIO')
					],
					usuario : adminUser).save(failOnError: true)

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
							tiposPersona : [
								SimCatTipoPersona.findByClaveTipoPersona('USUARIO')
							],
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
				).save(failOnError: true)

		new SimCatTipoAccesorio(claveTipoAccesorio: 'IVA',
				nombreTipoAccesorio: 'IVA',
				).save(failOnError: true)

		new SimCatTipoAccesorio(claveTipoAccesorio: 'RECARGO',
				nombreTipoAccesorio: 'RECARGOS',
				).save(failOnError: true)

		new SimCatTipoAccesorio(claveTipoAccesorio: 'CARGO_COMISION',
				nombreTipoAccesorio: 'CARGOS Y COMISIONES',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'INTERESES',
				nombreAccesorio: 'INTERESES',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('INTERES'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'IVA',
				nombreAccesorio: 'IVA CREDITO',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('IVA'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'MONTOFIJO',
				nombreAccesorio: 'MONTO FIJO POR PERIODO',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('RECARGO'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'MORATORIO',
				nombreAccesorio: 'INTERES MORATORIO',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('RECARGO'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'FIJOYMORATORIO',
				nombreAccesorio: 'MONTO FIJO E INTERES MORATORIO',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('RECARGO'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'SEGVID',
				nombreAccesorio: 'SEGURO DE VIDA',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
				tasaIva : 0,
				beneficiario : 'true',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'BURCRE',
				nombreAccesorio: 'CONSULTA BURO DE CREDITO',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)


		new SimCatAccesorio(claveAccesorio: 'SEGDEU',
				nombreAccesorio: 'SEGURO DEUDOR',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)

		new SimCatAccesorio(claveAccesorio: 'ADMCRE',
				nombreAccesorio: 'ADMINISTRACION CREDITICIA',
				tipoAccesorio : SimCatTipoAccesorio.findByClaveTipoAccesorio('CARGO_COMISION'),
				tasaIva : 0,
				beneficiario : 'false',
				).save(failOnError: true)

		new SimCatBanco(claveBanco: 'BANCOMER',
				nombreBanco: 'BANCOMER',
				).save(failOnError: true)

		new SimCatBanco(claveBanco: 'BANAMEX',
				nombreBanco: 'BANAMEX',
				).save(failOnError: true)

		new SimCatFormaEntrega(claveFormaEntrega: 'CAJA',
				nombreFormaEntrega: 'DIRECTAMENTE EN CAJA',
				).save(failOnError: true)

		new SimCatFormaEntrega(claveFormaEntrega: 'CHEQUE',
				nombreFormaEntrega: 'EN CHEQUE',
				).save(failOnError: true)

		new SimCatFormaEntrega(claveFormaEntrega: 'ELECTRONICA',
				nombreFormaEntrega: 'TRANSFERENCIA ELECTRONICA',
				).save(failOnError: true)

		new SimCatFormaEntrega(claveFormaEntrega: 'FONDOS',
				nombreFormaEntrega: 'DISPERCION DE FONDOS',
				).save(failOnError: true)

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE1',
				nombreDescripcionTelefono: 'CASA',
				).save(failOnError: true)

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE2',
				nombreDescripcionTelefono: 'OFICINA',
				).save(failOnError: true)

		new SimCatDescTelefono(claveDescripcionTelefono: 'CLAVE3',
				nombreDescripcionTelefono: 'FAX',
				).save(failOnError: true)

		new PfinDiaFestivo(diaFestivo:  new Date('01/01/2011'),
				descripcionDia: 'AÑO NUEVO',
				).save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumentacion:  'IDENTIFICACION',
				nombreTipoDocumentacion: 'IDENTIFICACION OFICIAL',
				).save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumentacion:  'DOMICILIO',
				nombreTipoDocumentacion: 'COMPROBANTE DE DOMICILIO',
				).save(failOnError: true)

		new SimCatTipoDocumento(claveTipoDocumentacion:  'LEGAL',
				nombreTipoDocumentacion: 'LEGAL',
				).save(failOnError: true)

		new SimCatReporte(claveReporte:  'CLAVE_1',
				nombreReporte: 'ANEXO A',
				descripcion: 'DESCRIPCION DEL ANEXO A',
				aplicaA : 'INDIVIDUAL',
				nombreFuncion: 'SimReporteAnexoA',
				).save(failOnError: true)

		new SimCatReporte(claveReporte:  'CLAVE_4',
				nombreReporte: 'PAGARE SOLIDARIO',
				descripcion: 'DESCRIPCION DEL PAGARE SOLIDARIO',
				aplicaA : 'GRUPO',
				nombreFuncion: 'SimReportePagareSolidario',
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'INGCRE',
				nombreDocumento: 'SOLICITUD',
				descripcion: 'FORMA PARA INGRESAR UN CRÉDITO',
				simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('IDENTIFICACION'),
				esReporte : 'false',
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'ANEXOA',
				nombreDocumento: 'ANEXO A',
				descripcion: 'FORMATO ANEXO A',
				simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('LEGAL'),
				simCatReporte : SimCatReporte.findByClaveReporte('CLAVE_1'),
				esReporte : 'true',
				).save(failOnError: true)

		new SimCatDocumento(claveDocumento:  'IFE',
				nombreDocumento: 'CREDENCIAL IFE',
				descripcion: 'CREDENCIAL IFE',
				simCatTipoDocumento : SimCatTipoDocumento.findByClaveTipoDocumentacion('IDENTIFICACION'),
				simCatReporte : SimCatReporte.findByClaveReporte('CLAVE_4'),
				esReporte : 'false',
				).save(failOnError: true)


		new SimCatEscolaridad(claveEscolaridad:  'CLAVE1',
				nombreEscolaridad: 'PRIMARIA',
				).save(failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'CLAVE2',
				nombreEscolaridad: 'SECUNDARIA',
				).save(failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'CLAVE3',
				nombreEscolaridad: 'PREPARATORIA',
				).save(failOnError: true)

		new SimCatEscolaridad(claveEscolaridad:  'CLAVE4',
				nombreEscolaridad: 'LICENCIATURA',
				).save(failOnError: true)

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'SOLICITADO',
				nombreEtapaPrestamo: 'SOLICITADO',
				descripcionEtapaPrestamo: 'SOLICITADO',
				).save(failOnError: true)

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'BURO',
				nombreEtapaPrestamo: 'BURO DE CREDITO',
				descripcionEtapaPrestamo: 'BURO DE CREDITO',
				).save(failOnError: true)


		new SimCatEtapaActividad(claveActividad: 'CHECKLIST',
				nombreActividad: 'CHECKLIST COORDINADOR',
				descripcionActividad: 'CHECKLIST COORDINADOR',
				etapa : SimCatEtapaPrestamo.findByClaveEtapaPrestamo('SOLICITADO')
				).save(failOnError: true)

		new SimCatEtapaActividad(claveActividad: 'EXPEDIENTE',
				nombreActividad: 'PREPARAR EXPEDIENTE',
				descripcionActividad: 'PREPARAR EXPEDIENTE',
				etapa : SimCatEtapaPrestamo.findByClaveEtapaPrestamo('SOLICITADO')
				).save(failOnError: true)

		new SimCatEtapaActividad(claveActividad: 'RECEPCION_CARTA',
				nombreActividad: 'RECEPCION SOLICITUD CARTA',
				descripcionActividad: 'RECEPCION SOLICITUD DE CARTA',
				etapa : SimCatEtapaPrestamo.findByClaveEtapaPrestamo('SOLICITADO')
				).save(failOnError: true)

		new SimCatEtapaActividad(claveActividad: 'SOLIC_VS_DOCTO',
				nombreActividad: 'REVISION SOLICITUD VS DOCUMENTACION',
				descripcionActividad: 'REVISION DE SOLICITUD VS DOCUMENTACION',
				etapa : SimCatEtapaPrestamo.findByClaveEtapaPrestamo('SOLICITADO')
				).save(failOnError: true)

		new SimCatFondeador(claveFondeador:  'CLAVE_1',
				nombreFondeador: 'PRONAFIN',
				).save(failOnError: true)

		new SimCatLineaFondeo(numeroLinea:  'LINEA 1',
				monto: 5000,
				montoDisponible: 5000,
				tasa: 15,
				fechaInicio: new Date('01/01/2011'),
				fechaVigencia : new Date('01/01/2015'),
				simCatFondeador : SimCatFondeador.findByClaveFondeador('CLAVE_1'),
				).save(failOnError: true)

		new SimCatLineaFondeo(numeroLinea:  'LINEA 2',
				monto: 25000,
				montoDisponible: 5000,
				tasa: 10,
				fechaInicio: new Date('01/01/2011'),
				fechaVigencia : new Date('01/01/2019'),
				simCatFondeador : SimCatFondeador.findByClaveFondeador('CLAVE_1'),
				).save(failOnError: true)


		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_01',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL E INTERES',
				descripcionMetodoCalculo: 'INTERES GLOBAL',
				).save(failOnError: true)

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_02',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'INTERES SOBRE SALDO INSOLUTO',
				).save(failOnError: true)

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_05',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO',
				descripcionMetodoCalculo: 'SIN RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				).save(failOnError: true)

		new SimCatMetodoCalculo(claveMetodoCalculo:  'CLAVE_06',
				nombreMetodoCalculo: 'PAGOS IGUALES DE CAPITAL MAS INTERES, CALCULO DE INTERES SOBRE EL SALDO INSOLUTO RI',
				descripcionMetodoCalculo: 'RECALCULO DE INTERESES POR PAGOS ADELANTADOS, CONOCIDO COMO METODO FRANCES',
				).save(failOnError: true)

		new SimCatParentesco(claveParentesco:  'MADRE',
				nombreParentesco: 'MADRE',
				).save(failOnError: true)

		new SimCatParentesco(claveParentesco:  'PADRE',
				nombreParentesco: 'PADRE',
				).save(failOnError: true)

		new SimCatPerfil(clavePerfil:  'CAJERO',
				nombrePerfil: 'CAJERO',
				).save(failOnError: true)

		new SimCatPerfil(clavePerfil:  'EJECRE',
				nombrePerfil: 'EJECUTIVO DE CREDITO',
				).save(failOnError: true)

		new SimCatPerfil(clavePerfil:  'GERENTE',
				nombrePerfil: 'GERENTE',
				).save(failOnError: true)


		new SimCatPeriodicidad(clavePeriodicidad:  'CLAVE_1',
				nombrePeriodicidad: 'SEMANAL',
				cantidadPagos: 1,
				numeroDias: 360,
				).save(failOnError: true)

		new SimCatPeriodicidad(clavePeriodicidad:  'CLAVE_2',
				nombrePeriodicidad: 'CATORCENAL',
				cantidadPagos: 12,
				numeroDias: 30,
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'DIRGEN',
				nombrePuesto: 'DIRECTOR GENERAL',
				descripcionPuesto: 'DIRECTOR GENERAL MICRO',
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'GERRIE',
				nombrePuesto: 'GERENTE DE RIESGOS',
				descripcionPuesto: 'GERENTE DE RIESGOS',
				dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'COORIE',
				nombrePuesto: 'COORDINADOR DE RIESGOS',
				descripcionPuesto: 'COORDINADOR DE RIESGOS',
				dependeDe : SimCatPuesto.findByClavePuesto('GERRIE'),
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'ASESORSUC',
				nombrePuesto: 'ASESOR DE SUCURSAL',
				descripcionPuesto: 'ASESOR DE SUCURSAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'GERREG',
				nombrePuesto: 'GERENTE REGIONAL',
				descripcionPuesto: 'GERENTE DE REGIONAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'COOREG',
				nombrePuesto: 'COORDINADOR REGIONAL',
				descripcionPuesto: 'COORDINADOR DE REGIONAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'GERSUC',
				nombrePuesto: 'GERENTE SUCURSAL',
				descripcionPuesto: 'GERENTE DE SUCURSAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
				).save(failOnError: true)

		new SimCatPuesto(clavePuesto:  'COOSUC',
				nombrePuesto: 'COORDINADOR SUCURSAL',
				descripcionPuesto: 'COORDINADOR DE SUCURSAL',
				dependeDe : SimCatPuesto.findByClavePuesto('DIRGEN'),
				).save(failOnError: true)

		new SimCatRechazoComite(claveRechazoComite:  '101',
				nombreRechazoComite: 'FALTA DE DOCUMENTOS',
				descripcionRechazoComite: 'FALTA DE DOCUMENTOS',
				).save(failOnError: true)

		new SimCatRechazoComite(claveRechazoComite:  '102',
				nombreRechazoComite: 'ANTECEDENTES PENALES ',
				descripcionRechazoComite: 'ANTECEDENTES PENALES ',
				).save(failOnError: true)

		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'CIUDAD',
				nombreTipoAsentamiento: 'CIUDAD',
				).save(failOnError: true)
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'COLONIA',
				nombreTipoAsentamiento: 'COLONIA',
				).save(failOnError: true)
		new SimCatTipoAsentamiento(claveTipoAsentamiento:  'URBANA',
				nombreTipoAsentamiento: 'URBANA',
				).save(failOnError: true)

		new SimCatTipoDomicilio(claveTipoDomicilio:  'CLAVE1',
				nombreTipoDomicilio: 'PROPIA',
				).save(failOnError: true)

		new SimCatTipoDomicilio(claveTipoDomicilio:  'CLAVE2',
				nombreTipoDomicilio: 'RENTADA',
				).save(failOnError: true)

		new SimCatTipoGarantia(claveTipoGarantia:  'CLAVE1',
				nombreTipoGarantia: 'PRENDARIA',
				descripcionTipoGarantia: 'DESCRIPCION PRENDARIA',
				requisitosTipoGarantia: 'REQUISITOS PRENDARIA',
				).save(failOnError: true)

		new SimCatTipoGarantia(claveTipoGarantia:  'CLAVE2',
				nombreTipoGarantia: 'QUIFOGRAFARIA',
				descripcionTipoGarantia: 'DESCRIPCION QUIFOGRAFARIA',
				requisitosTipoGarantia: 'REQUISITOS QUIFOGRAFARIA',
				).save(failOnError: true)

		new SimCatTipoIdentificador(claveTipoIdentificador:  'AVAL',
				nombreTipoIdentificador: 'AVAL O FIADOR',
				).save(failOnError: true)

		new SimCatTipoIdentificador(claveTipoIdentificador:  'CLIENTE',
				nombreTipoIdentificador: 'CLIENTE',
				).save(failOnError: true)

		new SimCatTipoIdentificador(claveTipoIdentificador:  'REGIONAL',
				nombreTipoIdentificador: 'REGIONAL',
				).save(failOnError: true)

		new SimCatTipoIdentificador(claveTipoIdentificador:  'SUCURSAL',
				nombreTipoIdentificador: 'SUCURSAL',
				).save(failOnError: true)

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE1',
				nombreTipoNegocio: 'FIJO',
				descripcionTipoNegocio: 'DESCRIPCION FIJO',
				).save(failOnError: true)

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE2',
				nombreTipoNegocio: 'SEMIFIJO',
				descripcionTipoNegocio: 'DESCRIPCION SEMIFIJO',
				).save(failOnError: true)

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE3',
				nombreTipoNegocio: 'AMBULANTE',
				descripcionTipoNegocio: 'DESCRIPCION AMBULANTE',
				).save(failOnError: true)

		new SimCatTipoNegocio(claveTipoNegocio:  'CLAVE4',
				nombreTipoNegocio: 'VENTAS POR CATALOGO',
				descripcionTipoNegocio: 'DESCRIPCION VENTAS CATALOGO',
				).save(failOnError: true)


		new SimCatVerificacionReferencia(claveTipoReferencia:  'CLAVE1',
				nombreTipoReferencia: 'REPUTACIÓN DE BUEN TRABAJADOR',
				descripcionTipoReferencia: 'REPUTACIÓN DE BUEN TRABAJADOR',
				).save(failOnError: true)


		new SimCatVerificacionReferencia(claveTipoReferencia:  'CLAVE4',
				nombreTipoReferencia: 'LO CONOCEN PERO NO SABEN SU CALIDAD COMO PERSONA',
				descripcionTipoReferencia: 'LO CONOCEN PERO NO SABEN SU CALIDAD COMO PERSONA',
				).save(failOnError: true)

		new SimCatTasaReferencia(claveTasaReferencia:  'CLAVE1',
				nombreTasaReferencia: 'CETES 28',
				descripcionTasaReferencia: 'DESCRIPCION CETES 28',
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1'),
				).save(failOnError: true)

		new SimCatTasaReferencia(claveTasaReferencia:  'CLAVE2',
				nombreTasaReferencia: 'BONO',
				descripcionTasaReferencia: 'DESCRIPCION BONO',
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_2'),
				).save(failOnError: true)

		new SimCatTasaPapel(claveTasaPapel:  'CLAVE1',
				fechaPublicacion: new Date('01/01/2011'),
				valorTasaPapel: 5.85,
				tasaReferencia: SimCatTasaReferencia.findByClaveTasaReferencia('CLAVE2'),
				).save(failOnError: true)

		new SimCatTasaPapel(claveTasaPapel:  'CLAVE2',
				fechaPublicacion: new Date('01/05/2011'),
				valorTasaPapel: 8.85,
				tasaReferencia: SimCatTasaReferencia.findByClaveTasaReferencia('CLAVE2'),
				).save(failOnError: true)

		new SimRegional(claveRegional:  'REGION1',
				nombreRegional: 'TOLUCA'
				).save(failOnError: true)

		new SimRegional(claveRegional:  'REGION2',
				nombreRegional: 'PUEBLA'
				).save(failOnError: true)

		new SimSucursal(claveSucursal:  'SUCURSAL1',
				nombreSucursal: 'SAN MATEO',
				regional : SimRegional.findByClaveRegional('REGION1')
				).save(failOnError: true)

		new SimSucursal(claveSucursal:  'SUCURSAL2',
				nombreSucursal: 'SANTIAGO TIANGUISTENCO',
				regional : SimRegional.findByClaveRegional('REGION1')
				).save(failOnError: true)

		new SimSucursal(claveSucursal:  'SUCURSAL3',
				nombreSucursal: 'IXTAPAN DE LA SAL',
				regional : SimRegional.findByClaveRegional('REGION1')
				).save(failOnError: true)

		new SimSucursalCaja(claveCaja:  'MCAJA1',
				nombreCaja: 'SAN MATEO CAJA1',
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1')
				).save(failOnError: true)

		new SimSucursalCaja(claveCaja:  'MCAJA2',
				nombreCaja: 'SAN MATEO CAJA2',
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1')
				).save(failOnError: true)

		new SimSucursalCaja(claveCaja:  'MCAJA3',
				nombreCaja: 'SAN MATEO CAJA3',
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1')
				).save(failOnError: true)


		new RsGralTelefono(telefono:  '111111111',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '222222222',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE3'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '33333333',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE1'),
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '444444444',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				sucursal : SimSucursal.findByClaveSucursal('SUCURSAL1'),
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '555555555',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '666666666',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE1'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save(failOnError: true)


		new Country(nameCountry: 'MEXICO',
				abbr: 'MEX',
				language: 'ESPANOL').save(failOnError: true)

		new Country(nameCountry: 'ESTADOS UNIDOS',
				abbr: 'USA',
				language: 'INGLES').save(failOnError: true)

		new City(nameCity: 'DISTRITO FEDERAL',
				timezone: 'DF',
				country : Country.findByNameCountry('MEXICO')).save(failOnError: true)

		new City(nameCity: 'ESTADO MEXICO',
				timezone: 'TOLUCA',
				country : Country.findByNameCountry('MEXICO')).save(failOnError: true)

		new City(nameCity: 'LOS ANGELES',
				timezone: 'LA',
				country : Country.findByNameCountry('ESTADOS UNIDOS')).save(failOnError: true)

		new City(nameCity: 'NEW YORK',
				timezone: 'NY',
				country : Country.findByNameCountry('ESTADOS UNIDOS')).save(failOnError: true)

		new RsGralEstado(cveEstado: 'AGS',
				nombreEstado: 'AGUASCALIENTES',
				aliasEstado : 'AGS').save(failOnError: true)

		new RsGralEstado(cveEstado: 'DF',
				nombreEstado: 'DISTRITO FEDERAL',
				aliasEstado : 'DF').save(failOnError: true)

		new RsGralEstado(cveEstado: 'EDOMEX',
				nombreEstado: 'ESTADO DE MEXICO',
				aliasEstado : 'EDOMEX').save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'EL COLORADO',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'AMAPOLAS DEL RIO',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'EL CONEJAL',
				estado : RsGralEstado.findByCveEstado('AGS')).save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'DISTRITO NORTE',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'DISTRITO SUR',
				estado : RsGralEstado.findByCveEstado('DF')).save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'METEPEC',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'CHIMALHUACAN',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralCiudad(nombreCiudad: 'MEXICO',
				estado : RsGralEstado.findByCveEstado('EDOMEX')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL COLORADO TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('EL COLORADO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL AMAPOLAS TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('AMAPOLAS DEL RIO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'EL CONEJAL TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('EL CONEJAL')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CUAUHTEMOC',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'VENUSTIANO CARRANZA',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'ALVARO OBREGON',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO NORTE')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'BENITO JUAREZ',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'COYOACAN',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'TLALPAN',
				ciudad : RsGralCiudad.findByNombreCiudad('DISTRITO SUR')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'METEPEC TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('METEPEC')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'CHIMALHUACAN TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('CHIMALHUACAN')).save(failOnError: true)


		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO UNO',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO DOS',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save(failOnError: true)

		new RsGralDelegacionMunicipio(nombreDelegacionMunicipio: 'MEXICO TRES',
				ciudad : RsGralCiudad.findByNombreCiudad('MEXICO')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO UNO',
				codigoPostal: '01000',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO DOS',
				codigoPostal: '01010',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 1 ASENTAMIENTO TRES',
				codigoPostal: '01020',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO UNO',
				codigoPostal: '01100',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO DOS',
				codigoPostal: '01110',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 2 ASENTAMIENTO TRES',
				codigoPostal: '01120',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO UNO',
				codigoPostal: '01200',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO DOS',
				codigoPostal: '01210',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL COLORADO 3 ASENTAMIENTO TRES',
				codigoPostal: '01220',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL COLORADO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO UNO',
				codigoPostal: '01230',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO DOS',
				codigoPostal: '01240',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 1 ASENTAMIENTO TRES',
				codigoPostal: '01250',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO UNO',
				codigoPostal: '01260',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO DOS',
				codigoPostal: '01270',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 2 ASENTAMIENTO TRES',
				codigoPostal: '01280',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO UNO',
				codigoPostal: '01290',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO DOS',
				codigoPostal: '01300',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL AMAPOLAS 3 ASENTAMIENTO TRES',
				codigoPostal: '01310',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL AMAPOLAS TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO UNO',
				codigoPostal: '01320',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO DOS',
				codigoPostal: '01330',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 1 ASENTAMIENTO TRES',
				codigoPostal: '01340',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO UNO',
				codigoPostal: '01350',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO DOS',
				codigoPostal: '01360',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 2 ASENTAMIENTO TRES',
				codigoPostal: '01370',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO UNO',
				codigoPostal: '01380',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO DOS',
				codigoPostal: '01390',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'EL CONEJAL 3 ASENTAMIENTO TRES',
				codigoPostal: '01400',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('EL CONEJAL TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'VISTA ALEGRE',
				codigoPostal: '06860',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'BUENOS AIRES',
				codigoPostal: '06861',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'BOTURINI',
				codigoPostal: '06862',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CUAUHTEMOC'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA UNO',
				codigoPostal: '06900',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA DOS',
				codigoPostal: '06910',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'VENUSTIANO CARRANZA TRES',
				codigoPostal: '06920',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('VENUSTIANO CARRANZA'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON UNO',
				codigoPostal: '01410',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON DOS',
				codigoPostal: '01420',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'ALVARO OBREGON TRES',
				codigoPostal: '01430',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('ALVARO OBREGON'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'SANTA CRUZ ATOYAC',
				codigoPostal: '01440',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'DEL VALLE',
				codigoPostal: '01450',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'NARVARTE',
				codigoPostal: '01460',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('COYOACAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ UNO',
				codigoPostal: '01470',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ DOS',
				codigoPostal: '01480',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'BENITO JUAREZ TRES',
				codigoPostal: '01490',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('BENITO JUAREZ'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN UNO',
				codigoPostal: '01500',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN DOS',
				codigoPostal: '01510',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'TLALPAN TRES',
				codigoPostal: '01520',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('TLALPAN'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO UNO',
				codigoPostal: '01530',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO DOS',
				codigoPostal: '01540',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 1 ASENTAMIENTO TRES',
				codigoPostal: '01550',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO UNO',
				codigoPostal: '01560',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO DOS',
				codigoPostal: '01570',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 2 ASENTAMIENTO TRES',
				codigoPostal: '01580',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO UNO',
				codigoPostal: '01590',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO DOS',
				codigoPostal: '01600',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'CHIMALHUACAN 3 ASENTAMIENTO TRES',
				codigoPostal: '01610',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('CHIMALHUACAN TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO UNO',
				codigoPostal: '01620',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO DOS',
				codigoPostal: '01630',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 1 ASENTAMIENTO TRES',
				codigoPostal: '01640',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO UNO',
				codigoPostal: '01650',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO DOS',
				codigoPostal: '01660',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 2 ASENTAMIENTO TRES',
				codigoPostal: '01670',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO UNO',
				codigoPostal: '01680',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO DOS',
				codigoPostal: '01690',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'METEPEC 3 ASENTAMIENTO TRES',
				codigoPostal: '01700',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('METEPEC TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)



		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO UNO',
				codigoPostal: '01710',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO DOS',
				codigoPostal: '01720',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 1 ASENTAMIENTO TRES',
				codigoPostal: '01730',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO UNO'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO UNO',
				codigoPostal: '01740',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO DOS',
				codigoPostal: '01750',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 2 ASENTAMIENTO TRES',
				codigoPostal: '01760',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO DOS'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO UNO',
				codigoPostal: '01770',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)


		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO DOS',
				codigoPostal: '01780',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralAsentamiento(nombreAsentamiento: 'MEXICO 3 ASENTAMIENTO TRES',
				codigoPostal: '01790',
				delegacionMunicipio : RsGralDelegacionMunicipio.findByNombreDelegacionMunicipio('MEXICO TRES'),
				tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA')).save(failOnError: true)

		new RsGralDomicilio(calle: 'Las Palmas',
				numeroInterior: '4',
				numeroExterior: '67',
				esFiscal: 'true',
				comentarios : 'ENFRENTE DE UNA FARMACIA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06860'),
				regional : SimRegional.findByClaveRegional('REGION1'),
				).save(failOnError: true)

		new RsGralDomicilio(calle: 'Direccion administrador',
				numeroInterior: '54',
				numeroExterior: '90',
				esFiscal: 'false',
				comentarios : 'CRUZANDO DE UNA AVENIDA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01790'),
				sucursal : SimSucursal.get(1),
				).save(failOnError: true)

		new RsGralDomicilio(calle: 'Direccion administrador',
				numeroInterior: '78',
				numeroExterior: '905',
				esFiscal: 'true',
				comentarios : 'ATRAS CENTRO COMERCIAL',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01580'),
				persona : RsPersona.get(1),
				).save(failOnError: true)

		new RsGralDomicilio(calle: 'BATALLONES ROJOS 205',
				numeroInterior: '504',
				numeroExterior: 'EDIF 8',
				esFiscal: 'true',
				comentarios : 'UNIDAD ALBARRADA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06862'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save(failOnError: true)

		new RsGralDomicilio(calle: 'PROGRESISTA',
				numeroInterior: '202',
				numeroExterior: 'EDIF 6',
				esFiscal: 'false',
				comentarios : 'UNIDAD VICENTE',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01600'),
				persona : RsPersona.findByEmail('mrugerio@gmail.com'),
				).save(failOnError: true)

		//ASIGNA ATRIBUTOS A LA PERSONA mrugerio@gmail.com
		def personaMiguel = RsPersona.findByEmail('mrugerio@gmail.com')
		personaMiguel.sexo = "MASCULINO"
		personaMiguel.estadoCivil = "CASADO - BIENES MANCOMUNADOS"
		personaMiguel.fechaNacimiento = new Date('09/30/1974')
		personaMiguel.nombreAlterno = "MIKE RUGEIRO"
		personaMiguel.identificacionOficial = SimCatDocumento.findByClaveDocumento('IFE')
		personaMiguel.numeroIdentificacionOficial = "RUFM727328328"
		personaMiguel.rfc = "RUFM89778"
		personaMiguel.curp = "RUMD76878968"
		personaMiguel.escolaridad  = SimCatEscolaridad.findByClaveEscolaridad('CLAVE4')
		personaMiguel.tiposPersona = [
			SimCatTipoPersona.findByClaveTipoPersona('USUARIO'),
			SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO')
		]
		personaMiguel.save(failOnError: true)

		// DA DE ALTA UN USUARIO
		def userHector = new Usuario(
				username: "hreyes",
				password: springSecurityService.encodePassword("hreyes"),
				enabled: true).save(failOnError: true)

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
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "RRHM727328328",
				rfc : "RRHM89778",
				curp : "RRHM76878968",
				escolaridad  : SimCatEscolaridad.findByClaveEscolaridad('CLAVE4'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('USUARIO'),
					SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO') ]
				).save(failOnError: true)

		//DA DE ALTA A UN EMPLEADO
		def empleado = new RsEmpleado(
				persona : personaHector,
				puesto : SimCatPuesto.findByClavePuesto('COOREG'),
				perfil : SimCatPerfil.findByClavePerfil('EJECRE'),
				sucursalPertenece : 1,
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
				).save(failOnError: true)


		def personaArturo = RsPersona.findByEmail('asalazar@example.org')
		//RECUPERO LA PERSONA Y LE AGREGA EL TIPO DE PERSONA IGUAL A EMPLEADO
		personaArturo.tiposPersona.add(SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO'))
		personaArturo.save(failOnError: true)

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  personaArturo,
				puesto : SimCatPuesto.findByClavePuesto('GERSUC'),
				perfil : SimCatPerfil.findByClavePerfil('EJECRE'),
				sucursalPertenece : 2,
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
				).save(failOnError: true)

		def personaChris = RsPersona.findByEmail('cgarcia@example.org')
		//RECUPERO LA PERSONA Y LE AGREGA EL TIPO DE PERSONA IGUAL A EMPLEADO
		personaChris.tiposPersona.add(SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO'))
		personaChris.save(failOnError: true)

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  personaChris,
				puesto : SimCatPuesto.findByClavePuesto('COOSUC'),
				perfil : SimCatPerfil.findByClavePerfil('EJECRE'),
				sucursalPertenece : 3,
				fechaIngreso  : new Date('12/30/2005'),
				numeroNomina : "003",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL1')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION1')]
				).save(failOnError: true)

		def personaEfren = RsPersona.findByEmail('egarcia@example.org')
		//RECUPERO LA PERSONA Y LE AGREGA EL TIPO DE PERSONA IGUAL A EMPLEADO
		personaEfren.tiposPersona.add(SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO'))
		personaEfren.save(failOnError: true)

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  personaEfren,
				puesto : SimCatPuesto.findByClavePuesto('GERREG'),
				perfil : SimCatPerfil.findByClavePerfil('GERENTE'),
				sucursalPertenece : 1,
				fechaIngreso  : new Date('12/05/2008'),
				numeroNomina : "004",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL2')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION1')]
				).save(failOnError: true)

		def personaMine = RsPersona.findByEmail('msalinas@somewhere.net')
		//RECUPERO LA PERSONA Y LE AGREGA EL TIPO DE PERSONA IGUAL A EMPLEADO
		personaMine.tiposPersona.add(SimCatTipoPersona.findByClaveTipoPersona('EMPLEADO'))
		personaMine.save(failOnError: true)

		//DA DE ALTA A UN EMPLEADO
		empleado = new RsEmpleado(
				persona :  personaMine,
				puesto : SimCatPuesto.findByClavePuesto('GERREG'),
				perfil : SimCatPerfil.findByClavePerfil('GERENTE'),
				sucursalPertenece : 2,
				fechaIngreso  : new Date('05/05/2009'),
				numeroNomina : "005",
				esVigente: 'true',
				asignarTodasSucursales: 'true',
				sucursalesConAcceso : [
					SimSucursal.findByClaveSucursal('SUCURSAL1')
				],
				regionalesConAcceso : [
					SimRegional.findByClaveRegional('REGION2')]
				).save(failOnError: true)

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
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "NAMA3328328",
				rfc : "SDFF89778",
				curp : "SDFSDF6878968",
				escolaridad  : SimCatEscolaridad.findByClaveEscolaridad('CLAVE2'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')]
				).save(failOnError: true)

		//DA DE ALTA UN CLIENTE
		def cliente = new RsCliente(
				persona :  personaCliente,
				ingresoSemanal : 4500.50,
				dependientesEconomicos : 4,
				destinoDelCredito : "AMPLIAR, ADECUAR O REPARAR EL LOCAL O VEHICULO",
				rolEnElHogar : "ESPOSO(A)",
				listaNegra : false).save(failOnError: true)

		//DA DE ALTA CUENTA BANCARIA A UN CLIENTE
		def cuentaBancaria = new RsCuentaBancaria(
				numeroDeCuenta : "122222222",
				clabe : "222222222",
				banco :  SimCatBanco.findByClaveBanco('BANAMEX'),
				cliente : cliente).save(failOnError: true)

		//DA DE ALTA CUENTA BANCARIA A UN CLIENTE
		cuentaBancaria = new RsCuentaBancaria(
				numeroDeCuenta : "111111111",
				clabe : "21111111",
				banco :  SimCatBanco.findByClaveBanco('BANCOMER'),
				cliente : cliente).save(failOnError: true)

		//DA DE ALTA UN DOCUMENTO A UN CLIENTE
		def documentoCliente = new RsClienteDocumentacion(
				cliente: cliente,
				documento : SimCatDocumento.findByClaveDocumento('INGCRE'),
				fechaRecibido : new Date('03/21/2011'),
				asesorVerifico : RsEmpleado.findByPersona(RsPersona.findByEmail('hreyes@credi.com')),
				documentacionCorrecta : true).save(failOnError: true)

		//DA DE ALTA UN DOCUMENTO A UN CLIENTE
		documentoCliente = new RsClienteDocumentacion(
				cliente: cliente,
				documento : SimCatDocumento.findByClaveDocumento('ANEXOA'),
				fechaRecibido : new Date('12/29/2011'),
				asesorVerifico : RsEmpleado.findByPersona(RsPersona.findByEmail('asalazar@example.org')),
				documentacionCorrecta : true).save(failOnError: true)

		//DA DE ALTA UNA PERSONA CLIENTE
		personaCliente = new RsPersona(
				apellidoPaterno: "HERNANDEZ",
				apellidoMaterno: "HERNANDEZ",
				primerNombre: "JAVO",
				email: "javo@hotmail.com",
				sexo : "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('03/21/1953'),
				nombreAlterno : "EL TEACHER",
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "NAMA3328328",
				rfc : "SDFF89778",
				curp : "SDFSDF6878968",
				escolaridad  : SimCatEscolaridad.findByClaveEscolaridad('CLAVE2'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('CLIENTE')]
				).save(failOnError: true)

		//DA DE ALTA UN CLIENTE
		cliente = new RsCliente(
				persona :  personaCliente,
				ingresoSemanal : 9500.50,
				dependientesEconomicos : 4,
				destinoDelCredito : "ADQUIRIR O COMPRAR MERCANCIA",
				rolEnElHogar : "ESPOSO(A)",
				listaNegra : false).save(failOnError: true)


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
				documentos : [
					SimCatDocumento.findByClaveDocumento('ANEXOA'),
					SimCatDocumento.findByClaveDocumento('INGCRE'),
				],
				asignarTodasSucursales : false).save(failOnError: true)


		//ASIGNA UN GERENTE Y COORDINADOR A LA REGION1
		def region = SimRegional.findByClaveRegional('REGION1')
		region.gerente = RsEmpleado.findByPersona(RsPersona.findByEmail('egarcia@example.org'))
		region.coordinador = RsEmpleado.findByPersona(RsPersona.findByEmail('hreyes@credi.com'))
		region.save(failOnError: true)

		//ASIGNA UN GERENTE Y COORDINADOR A LA SUCURSAL1
		def sucursal = SimSucursal.findByClaveSucursal('SUCURSAL1')
		sucursal.gerente = RsEmpleado.findByPersona(RsPersona.findByEmail('asalazar@example.org'))
		sucursal.coordinador = RsEmpleado.findByPersona(RsPersona.findByEmail('cgarcia@example.org'))
		sucursal.save(failOnError: true)

		//DA DE ALTA UNA PERSONA PARA REFERENCIA CLIENTE
		def personaReferencia = new RsPersona(
				apellidoPaterno: "PORTILLA",
				apellidoMaterno: "MARTINEZ",
				primerNombre: "CARLOS",
				sexo : "MASCULINO",
				estadoCivil : "SOLTERO",
				fechaNacimiento : new Date('03/21/1963'),
				nombreAlterno : "PERSY",
				identificacionOficial : SimCatDocumento.findByClaveDocumento('IFE'),
				numeroIdentificacionOficial : "NAMA3328329",
				rfc : "SDFF89779",
				curp : "SDFSDF6878969",
				escolaridad  : SimCatEscolaridad.findByClaveEscolaridad('CLAVE2'),
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('REFCLIENTE')]
				).save(failOnError: true)

		//DA DE ALTA UNA REFERENCIA CLIENTE
		def referenciaCliente = new RsReferenciaCliente(
				persona :  personaReferencia,
				tipoReferencia : "VECINAL",
				cliente : RsCliente.findByPersona(RsPersona.findByEmail('alex@hotmail.com'))).save(failOnError: true)

		new SimCatGiro(claveGiro: '461110',
				nombreGiro: 'Comercio al por menor en tiendas de abarrotes, ultramarinos y misceláneas',
				).save(failOnError: true)

		new SimCatGiro(claveGiro: '332110',
				nombreGiro: 'Fabricación de productos metálicos forjados y troquelados.',
				).save(failOnError: true)

		new SimCatGiro(claveGiro: '518110',
				nombreGiro: 'Proveedores de acceso a Internet y servicios de búsqueda en la red.',
				).save(failOnError: true)

		new SimCatUbicacionNegocio(claveUbicacionNegocio: 'UBICA1',
				nombreUbicacionNegocio: 'TIENDA, ACCESORIA O TENDEJON',
				).save(failOnError: true)

		new SimCatUbicacionNegocio(claveUbicacionNegocio: 'UBICA2',
				nombreUbicacionNegocio: 'PUESTO EN MERCADO BAJO TECHO COMUN',
				).save(failOnError: true)

		new SimCatUbicacionNegocio(claveUbicacionNegocio: 'UBICA3',
				nombreUbicacionNegocio: 'LOCALES BAJO TECHO COMUN',
				).save(failOnError: true)

		new SimCatUbicacionNegocio(claveUbicacionNegocio: 'UBICA4',
				nombreUbicacionNegocio: 'PASILLOS EN CENTRO COMERCIAL (KIOSCO)',
				).save(failOnError: true)

		new SimClienteNegocio(nombreNegocio: 'LA FLOR',
				rfc: 'KADLKDE9879',
				fechaInicioNegocio : new Date('03/21/1983'),
				personasTrabajando : 10,
				tipoNegocio : SimCatTipoNegocio.findByClaveTipoNegocio('CLAVE1'),
				giro : SimCatGiro.findByClaveGiro('518110'),
				ubicacionNegocio : SimCatUbicacionNegocio.findByClaveUbicacionNegocio('UBICA4'),
				cliente : RsCliente.findByPersona(RsPersona.findByEmail('alex@hotmail.com'))
				).save(failOnError: true)

		new SimClienteNegocio(nombreNegocio: 'LA MARGARITA',
				rfc: 'KADLKDE9856',
				fechaInicioNegocio : new Date('03/21/1999'),
				personasTrabajando : 25,
				tipoNegocio : SimCatTipoNegocio.findByClaveTipoNegocio('CLAVE4'),
				giro : SimCatGiro.findByClaveGiro('461110'),
				ubicacionNegocio : SimCatUbicacionNegocio.findByClaveUbicacionNegocio('UBICA3'),
				cliente : RsCliente.findByPersona(RsPersona.findByEmail('alex@hotmail.com'))
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '777777777',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE2'),
				negocio : SimClienteNegocio.findByNombreNegocio('LA FLOR'),
				).save(failOnError: true)

		new RsGralTelefono(telefono:  '888888888',
				descripcionTelefono : SimCatDescTelefono.findByClaveDescripcionTelefono('CLAVE3'),
				negocio : SimClienteNegocio.findByNombreNegocio('LA FLOR'),
				).save(failOnError: true)

		new RsGralDomicilio(calle: 'BATALLONES ROJOS 205',
				numeroInterior: '504',
				numeroExterior: 'EDIF 8',
				esFiscal: 'true',
				comentarios : 'UNIDAD ALBARRADA',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('06862'),
				negocio : SimClienteNegocio.findByNombreNegocio('LA FLOR'),
				).save(failOnError: true)

		new RsGralDomicilio(calle: 'PROGRESISTA',
				numeroInterior: '202',
				numeroExterior: 'EDIF 6',
				esFiscal: 'false',
				comentarios : 'UNIDAD VICENTE',
				rsGralAsentamiento : RsGralAsentamiento.findByCodigoPostal('01600'),
				negocio : SimClienteNegocio.findByNombreNegocio('LA FLOR'),
				).save(failOnError: true)

		//DA DE ALTA UNA PERSONA PARA UEF
		def personaUef = new RsPersona(
				apellidoPaterno: "SANCHEZ",
				apellidoMaterno: "HERNANDEZ",
				primerNombre: "JAVOS",
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('UEF')]
				).save(failOnError: true)

		//DA DE ALTA UNA UEF AL CLIENTE
		def uefCliente = new SimClienteIntegranteUef(
				persona :  personaUef,
				parentesco : SimCatParentesco.findByClaveParentesco('PADRE'),
				cliente : RsCliente.findByPersona(RsPersona.findByEmail('alex@hotmail.com'))).save(failOnError: true)

		//DA DE ALTA UN ADEUDO AL CLIENTE
		def adeudoCliente = new SimClienteAdeudos(
				nombreInstitucionDebe :  "MARIN PEREZ",
				montoPrestado : 50000.06,
				saldo : 5000.08,
				fechaPrestamo : new Date('03/21/1993'),
				frecuenciaPago : SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1'),
				cliente : RsCliente.findByPersona(RsPersona.findByEmail('alex@hotmail.com'))).save(failOnError: true)

		//DA DE ALTA UNA PERSONA PARA GARANTE DEPOSITARIO
		def personaGarante = new RsPersona(
				apellidoPaterno: "RUIZ",
				apellidoMaterno: "HERNANDEZ",
				primerNombre: "AARON",
				tiposPersona : [
					SimCatTipoPersona.findByClaveTipoPersona('GARDEP')]
				).save(failOnError: true)

		//DA DE ALTA UN GARANTE PRENDARIO
		def garanteDespositarioGarantia = new SimClienteGaranteDepositario(
				dependientesEconomicos : 10,
				listaNegra : false,
				persona : personaGarante
				).save(failOnError: true)

		//DA DE ALTA UNA GARANTIA
		def garantiaCliente = new SimClienteGarantia(
				tipoGarantia : SimCatTipoGarantia.findByClaveTipoGarantia('CLAVE2'),
				descripcionGarantia : 'FACTURA DE CHEVY',
				numeroFacturaEscritura : 'ADASDF89798',
				fechaFacturaEscritura : new Date('03/21/2002'),
				valorComercialPesos : 5900.76,
				valorGarantizaPesos : 3434.67,
				porcentajeCubreGarantia : 80,
				garanteDepositario : garanteDespositarioGarantia,
				cliente : RsCliente.findByPersona(RsPersona.findByEmail('alex@hotmail.com'))
				).save(failOnError: true)

		//DA DE ALTA UN PRODUCTO CICLO
		def productoCicloUno = new SimProductoCiclo(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				numeroCiclo : 1,
				distribucionPago : 'ACCESORIOS-CAPITAL',
				plazo : 8,
				tasa : 12.8,
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1'),
				montoMaximo : 9434.67,
				porcentajeFlujoCajaFinanciar : 80,
				recargoMontoFijo : 50
				).save(failOnError: true)

		//DA DE ALTA UN PRODUCTO CICLO
		def productoCicloDos = new SimProductoCiclo(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				numeroCiclo : 2,
				distribucionPago : 'ACCESORIOS-CAPITAL',
				plazo : 10,
				tasa : 11.8,
				periodicidadTasa: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1'),
				montoMaximo : 10000.00,
				porcentajeFlujoCajaFinanciar : 85,
				recargoMontoFijo : 50
				).save(failOnError: true)

		//DA DE ALTA UN PRODUCTO CARGO COMISION
		def productoCargoComision = new SimProductoCargoComision(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				cargoComision :SimCatAccesorio.findByClaveAccesorio('SEGDEU'),
				formaAplicacion : 'PERIODICAMENTE DEPENDIENDO DEL MONTO PRESTADO',
				valor : 1,
				unidad : 'AL MILLAR',
				periodicidadValorUnidad: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_1')
				).save(failOnError: true)

		//DA DE ALTA UN PRODUCTO CARGO COMISION
		productoCargoComision = new SimProductoCargoComision(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				cargoComision :SimCatAccesorio.findByClaveAccesorio('BURCRE'),
				formaAplicacion : 'CARGO INICIAL',
				cargoInicial : 150
				).save(failOnError: true)

		//DA DE ALTA UN PRODUCTO CARGO COMISION
		productoCargoComision = new SimProductoCargoComision(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				cargoComision :SimCatAccesorio.findByClaveAccesorio('ADMCRE'),
				formaAplicacion : 'PERIODICAMENTE DEPENDIENDO DEL MONTO PRESTADO',
				valor : 0.4,
				unidad : 'PORCENTUAL',
				periodicidadValorUnidad: SimCatPeriodicidad.findByClavePeriodicidad('CLAVE_2')
				).save(failOnError: true)


		def productoCicloAccesorio = new SimProductoCicloAccesorios(
				productoCiclo : productoCicloUno,
				accesorio : SimCatAccesorio.findByClaveAccesorio('ADMCRE'),
				orden : 1
				).save(failOnError: true)

		productoCicloAccesorio = new SimProductoCicloAccesorios(
				productoCiclo : productoCicloUno,
				accesorio : SimCatAccesorio.findByClaveAccesorio('BURCRE'),
				orden : 2
				).save(failOnError: true)

		productoCicloAccesorio = new SimProductoCicloAccesorios(
				productoCiclo : productoCicloUno,
				accesorio : SimCatAccesorio.findByClaveAccesorio('SEGDEU'),
				orden : 3
				).save(failOnError: true)

		new SimProductoEtapaActividad(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				etapaActividad : SimCatEtapaActividad.findByClaveActividad('CHECKLIST'),
				orden: 1
				).save(failOnError: true)

		new SimProductoEtapaActividad(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				etapaActividad : SimCatEtapaActividad.findByClaveActividad('RECEPCION_CARTA'),
				orden: 2
				).save(failOnError: true)

		new SimProductoEtapaActividad(
				producto : SimProducto.findByClaveProducto('SOLIDARIO'),
				etapaActividad : SimCatEtapaActividad.findByClaveActividad('EXPEDIENTE'),
				orden: 3
				).save(failOnError: true)

		new SimGrupo(
				claveGrupo : '001',
				nombreGrupo : 'LOS 4 FANTASTICOS',
				fechaInicioActivacion: new Date('01/20/2008'),
				integrantes: [
					RsCliente.findByPersona(RsPersona.findByEmail('javo@hotmail.com')),
					RsCliente.findByPersona(RsPersona.findByEmail('alex@hotmail.com'))]
				).save(failOnError: true)

		//IMPLEMENTACION DE SEGURIDAD A NIVEL Dynamic request maps
		new Requestmap(url: '/user/**', configAttribute: 'ROLE_ADMIN').save(failOnError: true)
		new Requestmap(url: '/rsConfGpoEmpresa/**', configAttribute: 'ROLE_USER').save(failOnError: true)
		new Requestmap(url: '/rsConfEmpresa/create', configAttribute: 'ROLE_USER').save(failOnError: true)
	}

	def destroy = {
	}

	private getOrCreateRole(name) {
		def role = SecRole.findByAuthority(name)
		if (!role) role = new SecRole(authority: name).save(failOnError: true)
		if (!role)  println "Unable to save role ${name}"
		return role
	}
}
