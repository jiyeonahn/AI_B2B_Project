package com.three_iii.company.presentation;

import com.three_iii.company.application.dtos.product.ProductResponse;
import com.three_iii.company.application.service.ProductService;
import com.three_iii.company.exception.Response;
import com.three_iii.company.presentation.dtos.ProductCreateRequest;
import com.three_iii.company.presentation.dtos.ProductUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Product CRUD")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "상품 생성", description = "상품을 생성한다.")
    public Response<ProductResponse> createProduct(
        @RequestBody @Valid ProductCreateRequest request) {
        return Response.success(productService.createProduct(request.toDTO()));
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 단건 조회", description = "상품을 단건 조회한다.")
    public Response<ProductResponse> findProduct(@PathVariable UUID productId) {
        return Response.success(productService.findProduct(productId));
    }

    @GetMapping
    @Operation(summary = "상품 전체 조회", description = "상품을 전체 조회한다.")
    public Response<Page<ProductResponse>> findAllProduct(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) UUID hubId,
        @RequestParam(required = false) UUID companyId,
        Pageable pageable) {
        return Response.success(productService.findAllProduct(keyword, hubId, companyId, pageable));
    }

    @PatchMapping("/{productId}")
    @Operation(summary = "상품 수정", description = "상품을 수정한다.")
    public Response<ProductResponse> updateProduct(@PathVariable UUID productId,
        @RequestBody ProductUpdateRequest request) {
        return Response.success(productService.updateProduct(request.toDTO(), productId));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "상품 삭제", description = "상품을 삭제한다.")
    public Response<?> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return Response.success("해당 상품이 삭제되었습니다.");
    }
}