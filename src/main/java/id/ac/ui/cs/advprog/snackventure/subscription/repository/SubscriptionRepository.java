package id.ac.ui.cs.advprog.snackventure.subscription.repository;

import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID>{
    List<Subscription> findByCustomerId(String id);
}
