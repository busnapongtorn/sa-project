package cs.ku.sa_project.repositories;

import cs.ku.sa_project.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
