package br.com.fiap.wtc.repository;

import br.com.fiap.wtc.model.CampaignDelivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignDeliveryRepository extends MongoRepository<CampaignDelivery, String> {
    List<CampaignDelivery> findByCampaignId(String campaignId);
}