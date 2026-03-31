package br.com.fiap.wtc.service;

import br.com.fiap.wtc.model.Campaign;
import br.com.fiap.wtc.model.CampaignDelivery;
import br.com.fiap.wtc.model.Client;
import br.com.fiap.wtc.repository.CampaignDeliveryRepository;
import br.com.fiap.wtc.repository.CampaignRepository;
import br.com.fiap.wtc.repository.ClientRepository;
import br.com.fiap.wtc.repository.SegmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignDeliveryRepository campaignDeliveryRepository;
    private final ClientRepository clientRepository;
    private final SegmentRepository segmentRepository;

    public CampaignService(CampaignRepository campaignRepository,
                           CampaignDeliveryRepository campaignDeliveryRepository,
                           ClientRepository clientRepository,
                           SegmentRepository segmentRepository) {
        this.campaignRepository = campaignRepository;
        this.campaignDeliveryRepository = campaignDeliveryRepository;
        this.clientRepository = clientRepository;
        this.segmentRepository = segmentRepository;
    }

    public Campaign create(Campaign campaign) {
        if (campaign.getSegmentId() != null && !campaign.getSegmentId().isBlank()) {
            boolean segmentExists = segmentRepository.existsById(campaign.getSegmentId());
            if (!segmentExists) {
                return null;
            }
        }

        campaign.setId(null);
        campaign.setCreatedAt(LocalDateTime.now());
        return campaignRepository.save(campaign);
    }

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    public Campaign findById(String id) {
        return campaignRepository.findById(id).orElse(null);
    }

    public List<CampaignDelivery> send(String campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId).orElse(null);

        if (campaign == null) {
            return List.of();
        }

        List<Client> clients = clientRepository.findAll();

        List<CampaignDelivery> deliveries = clients.stream().map(client -> {
            CampaignDelivery delivery = new CampaignDelivery();
            delivery.setCampaignId(campaign.getId());
            delivery.setClientId(client.getId());
            delivery.setStatus("SENT");
            delivery.setDeliveredAt(LocalDateTime.now());
            delivery.setReadAt(null);
            return campaignDeliveryRepository.save(delivery);
        }).toList();

        return deliveries;
    }

    public List<CampaignDelivery> findDeliveriesByCampaignId(String campaignId) {
        return campaignDeliveryRepository.findByCampaignId(campaignId);
    }
}