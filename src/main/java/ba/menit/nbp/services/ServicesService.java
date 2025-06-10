package ba.menit.nbp.services;

import ba.menit.nbp.dtos.ServiceDto;
import ba.menit.nbp.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public ServiceDto createService(ServiceDto serviceDto) {
        ba.menit.nbp.entities.Service newService = new ba.menit.nbp.entities.Service();
        newService.setName(serviceDto.getName());
        newService.setPrice(serviceDto.getPrice());
        newService.setDurationInMin(serviceDto.getDurationInMin());
        ba.menit.nbp.entities.Service savedService = serviceRepository.save(newService);
        return convertToDto(savedService);
    }


    public void deleteService(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Service not found with ID: " + id);
        }
        serviceRepository.deleteById(id);
    }


    private ServiceDto convertToDto(ba.menit.nbp.entities.Service service) { // Fully qualify entity 'Service'
        return new ServiceDto(service.getId(), service.getName(), service.getPrice(), service.getDurationInMin());
    }


}