package net.kozinaki.faktura;

import java.util.List;
import net.kozinaki.faktura.domain.entities.Customer;
import net.kozinaki.faktura.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

  private final CustomerRepository repository;

  @Autowired
  public DatabaseLoader(CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Customer> customers = List.of(
        new Customer(
            "Davion, the Dragon Knight",
            "+79876543210",
            "Breathe Fire, Dragon Tail, Dragon Blood, Fireball, Elder Dragon Form",
            "dragonknight@dota2.com",
            "0123456789",
            "The Dragon Slyrak sleeps within this armor, and the knight within the Dragon waits. Beware you do not wake them both."
        ),
        new Customer(
            "Chaos Knight",
            "+79012345678",
            "From a far upstream plane where the fundamental laws of the universe have found sentient expression",
            "chaosknight@dota2.com",
            "9876543210",
            "The light shall be blackened, and chaos shall reign.\nChaos Bolt, Reality Rift, Chaos Strike, Phantasm"
        ),
        new Customer(
            "Omniknight",
            "+7954321678",
            "Purist Thunderwrath was a hard-fighting, road-worn, deeply committed knight, sworn to the order in which he had grown up as squire to elder knights of great reputation",
            "omniknight@dota2.com",
            "5432109876",
            "I have gazed into the Omniscience, and it has gazed into me.\nPurification, Heavenly Grace, Hammer of Purity, Degen Aura, Guardian Angel"
        )
    );


    this.repository.saveAll(customers);
  }
}
