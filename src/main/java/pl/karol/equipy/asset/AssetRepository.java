package pl.karol.equipy.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    @Query("select a from Asset a where upper(a.name) like upper(concat('%',:text,'%')) " +
            "or upper(a.serialNumber) like upper(concat('%',:text,'%'))")
    List<Asset> findAllByNameOrSerialNumber(@Param("text") String text);

    Optional<Asset> findBySerialNumber(String serialNumber);
}
