import com.sim.empresa.RsConfGpoEmpresa
import com.sim.empresa.RsConfEmpresa

class BootStrap {

    def init = { servletContext ->
		new RsConfGpoEmpresa(claveGrupoEmpresa: 'SIM',
							nombreGrupoEmpresa: 'SIM CREDITOS',
							fechaCreacion: new Date('01/01/2011')).save()

		new RsConfEmpresa(claveEmpresa: 'CREDITOS',
							nombreEmpresa: 'MICROFINANCIERA AZUL',
							fechaCreacion: new Date('01/01/2011'),
							rsConfGpoEmpresa: RsConfGpoEmpresa.findByClaveGrupoEmpresa('SIM')).save()

    }


    def destroy = {
    }

}
