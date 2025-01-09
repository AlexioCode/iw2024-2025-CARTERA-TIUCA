package es.uca.iw.carteratiuca.services;

import es.uca.iw.carteratiuca.entities.*;
import es.uca.iw.carteratiuca.repositories.JustificacionProyectoRepository;
import es.uca.iw.carteratiuca.repositories.ProyectoRepository;
import jakarta.validation.constraints.Email;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final JustificacionProyectoRepository justificacionProyectoRepository;

    public ProyectoService(ProyectoRepository repository, JustificacionProyectoRepository justificacionProyectoRepository) {
        this.proyectoRepository = repository;
        this.justificacionProyectoRepository = justificacionProyectoRepository;
    }

    public List<Proyecto> getProyectosBySolicitante(User solicitante) {
        return proyectoRepository.findBySolicitante(solicitante);
    }

    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    public List<Proyecto> getProyectosSinArchivar() {
        List<Proyecto> proyectosNoArchivados = new ArrayList<Proyecto>();
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.ACEPTADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.DENEGADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.NOTERMINADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.REGISTRADO)));
        proyectosNoArchivados.addAll((proyectoRepository.findByEstado(EstadoProyecto.POSPUESTO)));

        return proyectosNoArchivados;
    }

    @Transactional
    public void registerProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Transactional
    public void update(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Transactional
    public void delete(UUID id) {
        Proyecto proyecto = proyectoRepository.findById(id).get();
        UUID justificacionId = proyecto.getJustificacion().getId();

        proyectoRepository.deleteById(id);
        justificacionProyectoRepository.deleteById(justificacionId);
    }

    public List<Proyecto> getProyectosDePromotor(User user) {
        return proyectoRepository.findByPromotor(user);
    }

    public List<Proyecto> getProyectosDePromotorPendientesDeAvalar(User user) {
        return proyectoRepository.findByPromotorAndEstadoAvalacion(user, EstadosAvalacionValoracion.PORDETERMINAR);
    }

    public List<Proyecto> getProyectosAvaladosSinValorarCIO() {
        return proyectoRepository.findByEstadoAvalacionAndEstadoValoracionCIO(EstadosAvalacionValoracion.SI,
                EstadosAvalacionValoracion.PORDETERMINAR);
    }

    public List<Proyecto> getProyectosAvaladosSinValorarOTP() {
        return proyectoRepository.findByEstadoAvalacionAndEstadoValoracionCIO(EstadosAvalacionValoracion.SI,
                EstadosAvalacionValoracion.PORDETERMINAR);
    }

    public List<Proyecto> getProyectosValoradosPorCIOyOTP() {
        return proyectoRepository.findByEstadoValoracionCIOAndEstadoValoracionOTPAndEstado(
                EstadosAvalacionValoracion.SI, EstadosAvalacionValoracion.SI, EstadoProyecto.REGISTRADO);
    }

    public void calcularValoracionCIO(Proyecto proyecto) {
        JustificacionProyecto justificacion = proyecto.getJustificacion();
        int valoracionCIO = proyecto.getValoracionFinal();

        // Lista de métodos booleanos de JustificacionProyecto
        List<Boolean> criterios = List.of(
                justificacion.isActualizarOferta(),
                justificacion.isAltaCalidad(),
                justificacion.isAumentarInvestigacion(),
                justificacion.isConseguirTransparencia(),
                justificacion.isConsolidarGobiernoSostenible(),
                justificacion.isGenerarValorCompartido(),
                justificacion.isReforzarPapelUCA()
        );

        // Incrementa la valoración por cada criterio cumplido
        valoracionCIO += criterios.stream().filter(Boolean::booleanValue).mapToInt(c -> 5000).sum();

        proyecto.setValoracionCIO(valoracionCIO);
        proyecto.setValoracionFinal(proyecto.getValoracionFinal() + valoracionCIO);
    }

    public int valoracionOTP(Proyecto proyecto)
    {
        return proyecto.getFinanciacionInteresado()
                .divide( new BigDecimal(proyecto.getNumEmpleados()))
                .multiply(new BigDecimal(proyecto.getImportanciaPromotor())).toBigInteger().intValue();
    }

    public long count ()
    {
        return proyectoRepository.count();
    }

}