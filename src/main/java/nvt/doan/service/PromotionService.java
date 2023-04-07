package nvt.doan.service;

import nvt.doan.entities.Promotion;

import java.util.Optional;

public interface PromotionService extends BaseService<Promotion,Integer> {
    Optional<Promotion> getPromotionById(Integer promotionId);
}
