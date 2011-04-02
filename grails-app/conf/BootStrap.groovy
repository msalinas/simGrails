import com.sim.empresa.*
import com.sim.catalogo.*
import com.sim.pfin.*
import com.sim.usuario.*
import org.example.User

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

		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'CLAVE_1',
								nombreEtapaPrestamo: 'SOLICITADO',
								descripcionEtapaPrestamo: 'SOLICITADO',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
		
		new SimCatEtapaPrestamo(claveEtapaPrestamo:  'CLAVE_2',
								nombreEtapaPrestamo: 'BURO DE CREDITO',
								descripcionEtapaPrestamo: 'BURO DE CREDITO',
								rsConfEmpresa: RsConfEmpresa.findByClaveEmpresa('CREDITOS')).save()
		
									
																		
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
