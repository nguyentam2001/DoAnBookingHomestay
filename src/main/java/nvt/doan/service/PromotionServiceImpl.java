package nvt.doan.service;

import nvt.doan.entities.Promotion;
import nvt.doan.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component("promotionServiceImpl")
public class PromotionServiceImpl extends BaseServiceImpl<Promotion,Integer> implements PromotionService{
    @Autowired
    PromotionRepository promotionRepository;
    @Override
    public Optional<Promotion> getPromotionById(Integer promotionId) {
        return promotionRepository.findById(promotionId);
    }
}
