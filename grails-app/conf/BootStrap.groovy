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
			'chuck_norris' : [ fullName: 'Chuck Norris', email: "chuck@example.org" ],
			'mike' : [ fullName: 'Miguel Rugerio', email: "mike@example.org" ],
			'peter' : [ fullName: 'Peter Ledbrook', email: "peter@somewhere.net" ],
			'sven' : [ fullName: 'Sven Haiges', email: "sven@example.org" ],
			'burt' : [fullName : 'Burt Beckwith', email: "burt@somewhere.net" ] ]
		
		def userRole = getOrCreateRole("ROLE_USER")
		def adminRole = getOrCreateRole("ROLE_ADMIN")
		
		def users = User.list() ?: []
		if (!users) {
			// Start with the admin user.
			def adminUser = new User(
					username: "admin",
					password: springSecurityService.encodePassword("4321"),
					enabled: true).save()
			SecUserSecRole.create adminUser, adminRole
			
			// Now the normal users.
			samples.each { username, profileAttrs ->
				def user = new User(
						username: username,
						password: springSecurityService.encodePassword("1234"),
						enabled: true)
				if (user.validate()) {
					println "Creating user ${username}..."

					user.save(flush:true)
					
					def rel = SecUserSecRole.create(user, userRole)
					if (rel.hasErrors()) println "Failed to assign user role to ${user.username}: ${rel.errors}"
					
					users << user
				}
				else {
					println("\n\n\nError in account bootstrap for ${username}!\n\n\n")
					user.errors.each {err ->
						println err
					}
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
									
		new RsGralCodigoPostal(codigoPostal:  '06860',
								codigo: '06860',
								tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA'),
								asentamiento: 'COLONIA',
								ciudad: 'DISTRITO FEDERAL',
								estado: 'DISTRITO FEDERAL',
								municipio: 'CUAUHTEMOC',
								oficina: 'UNO').save()
	
		new RsGralCodigoPostal(codigoPostal:  '09370',
								codigo: '09370',
								tipoAsentamiento: SimCatTipoAsentamiento.findByClaveTipoAsentamiento('COLONIA'),
								asentamiento: 'COLONIA',
								ciudad: 'DISTRITO FEDERAL',
								estado: 'DISTRITO FEDERAL',
								municipio: 'BENITO JUAREZ',
								oficina: 'DOS').save()

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
								claveIdentificador : SimCatTipoIdentificador.findByClaveTipoIdentificador('REGIONAL'),
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimRegional(claveRegional:  'REGION2',
								nombreRegional: 'PUEBLA',
								gerente: 'ARMANDO',
								coordinador: 'GILBERTO',
								claveIdentificador : SimCatTipoIdentificador.findByClaveTipoIdentificador('REGIONAL'),
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
								
		new SimSucursal(claveSucursal:  'SUCURSAL1',
								nombreSucursal: 'SAN MATEO',
								gerente: 'ARMANDO',
								coordinador: 'GILBERTO',
								regional : SimRegional.findByClaveRegional('REGION1'),
								claveIdentificador : SimCatTipoIdentificador.findByClaveTipoIdentificador('SUCURSAL'),
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimSucursal(claveSucursal:  'SUCURSAL2',
								nombreSucursal: 'SANTIAGO TIANGUISTENCO',
								gerente: 'MARIO',
								coordinador: 'GUSTAVO',
								regional : SimRegional.findByClaveRegional('REGION1'),
								claveIdentificador : SimCatTipoIdentificador.findByClaveTipoIdentificador('SUCURSAL'),
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()

		new SimSucursal(claveSucursal:  'SUCURSAL3',
								nombreSucursal: 'IXTAPAN DE LA SAL',
								gerente: 'MARIO',
								coordinador: 'GUSTAVO',
								regional : SimRegional.findByClaveRegional('REGION1'),
								claveIdentificador : SimCatTipoIdentificador.findByClaveTipoIdentificador('SUCURSAL'),
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
	
		new RsGralDomicilio(calle: 'Las Palmas',
								numeroInterior: '4',
								numeroExterior: '67',
								esFiscal: 'true',
								comentarios : 'ENFRENTE DE UNA FARMACIA',
								codigoPostal : RsGralCodigoPostal.findByCodigoPostal('09370'),
								regional : SimRegional.findByClaveRegional('REGION1'),
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
								
		new Country(name: 'MEXICO',
							abbr: 'MEX',
							language: 'ESPANOL').save()
								
		new Country(name: 'ESTADOS UNIDOS',
								abbr: 'USA',
								language: 'INGLES').save()
								
		new City(name: 'DISTRITO FEDERAL',
								timezone: 'DF',
								country : Country.findByName('MEXICO')).save()
								
		new City(name: 'ESTADO MEXICO',
									timezone: 'TOLUCA',
									country : Country.findByName('MEXICO')).save()

		new City(name: 'LOS ANGELES',
										timezone: 'LA',
										country : Country.findByName('ESTADOS UNIDOS')).save()

		new City(name: 'NEW YORK',
											timezone: 'NY',
											country : Country.findByName('ESTADOS UNIDOS')).save()
	
									
		//Ejemplo para implementar roles a nivel de base de datos
		//new Requestmap(url: '/simCatBanco/**', configAttribute: 'ROLE_ADMIN').save()
								
																		
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
