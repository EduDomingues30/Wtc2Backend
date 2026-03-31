package br.com.fiap.wtc.controller;

import br.com.fiap.wtc.model.Campaign;
import br.com.fiap.wtc.model.CampaignDelivery;
import br.com.fiap.wtc.service.CampaignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    public ResponseEntity<Campaign> create(@RequestBody Campaign campaign) {
        Campaign created = campaignService.create(campaign);

        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> findAll() {
        return ResponseEntity.ok(campaignService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> findById(@PathVariable String id) {
        Campaign campaign = campaignService.findById(id);
        if (campaign == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(campaign);
    }

    @PostMapping("/{id}/send")
    public ResponseEntity<List<CampaignDelivery>> send(@PathVariable String id) {
        List<CampaignDelivery> deliveries = campaignService.send(id);

        if (deliveries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/{id}/deliveries")
    public ResponseEntity<List<CampaignDelivery>> findDeliveries(@PathVariable String id) {
        return ResponseEntity.ok(campaignService.findDeliveriesByCampaignId(id));
    }
}