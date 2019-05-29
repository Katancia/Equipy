package pl.karol.equipy.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.karol.equipy.category.Category;
import pl.karol.equipy.category.CategoryRepository;

import java.util.Optional;

@Component
public class AssetMapper {

    private CategoryRepository categoryRepository;

    @Autowired
    public AssetMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Asset toEntity(AssetDto assetDto) {
        Asset asset = new Asset();
        asset.setId(assetDto.getId());
        asset.setName(assetDto.getName());
        asset.setDescription(assetDto.getDescription());
        asset.setSerialNumber(assetDto.getSerialNumber());
        Optional<Category> category = categoryRepository.findByName(assetDto.getCategory());
        category.ifPresent(asset::setCategory);
        return asset;
    }

    AssetDto toDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.setId(asset.getId());
        assetDto.setName(asset.getName());
        assetDto.setDescription(asset.getDescription());
        assetDto.setSerialNumber(asset.getSerialNumber());
        if(asset.getCategory() != null)
            assetDto.setCategory(asset.getCategory().getName());
        return assetDto;
    }
}
