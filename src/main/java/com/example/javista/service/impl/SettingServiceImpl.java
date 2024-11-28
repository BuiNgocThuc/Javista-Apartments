package com.example.javista.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.javista.dto.request.setting.SettingPatchRequest;
import com.example.javista.dto.request.setting.SettingUpdateRequest;
import com.example.javista.dto.response.setting.SettingResponse;
import com.example.javista.entity.Bill;
import com.example.javista.entity.BillDetail;
import com.example.javista.entity.Relationship;
import com.example.javista.entity.Setting;
import com.example.javista.enums.ApartmentStatus;
import com.example.javista.enums.BillStatus;
import com.example.javista.enums.RelationshipRole;
import com.example.javista.enums.SystemStatus;
import com.example.javista.exception.AppException;
import com.example.javista.exception.ErrorCode;
import com.example.javista.mapper.SettingMapper;
import com.example.javista.repository.*;
import com.example.javista.service.SettingService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SettingServiceImpl implements SettingService {
    SettingMapper settingMapper;
    SettingRepository settingRepository;

    RelationshipRepository relationshipRepository;
    ServiceRepository serviceRepository;
    BillRepository billRepository;
    ApartmentRepository apartmentRepository;

    public Setting getSetting() {
        return settingRepository.findAll().getFirst();
    }

    @Override
    public SettingResponse getCurrentSetting() {
        return settingMapper.entityToResponse(getSetting());
    }

    @Override
    public SettingResponse updateSetting(Integer id, SettingUpdateRequest request) {
        Setting setting = getSetting();
        settingMapper.updateRequestToEntity(setting, request);
        return settingMapper.entityToResponse(settingRepository.save(setting));
    }

    @Override
    public SettingResponse patchSetting(Integer id, SettingPatchRequest request) {
        Setting setting = getSetting();
        settingMapper.patchRequestToEntity(setting, request);
        return settingMapper.entityToResponse(settingRepository.save(setting));
    }

    @Override
    public SettingResponse updateStatusPrepayment() {
        Setting setting = getSetting();
        // get current monthly period
        String currentMonthly = setting.getCurrentMonthly();

        List<Relationship> owners = relationshipRepository.findByRoleAndDeletedAtNull(RelationshipRole.OWNER);

        List<com.example.javista.entity.Service> services = serviceRepository.findByDeletedAtNull();

        for (Relationship owner : owners) {
            Bill prepaidBill = billRepository.findByRelationshipAndMonthly(owner, currentMonthly);
            if (prepaidBill == null) {
                // create new bill details
                Set<BillDetail> billDetails = new HashSet<>();
                Float totalServicePrice = 0f;
                for (com.example.javista.entity.Service service : services) {
                    BillDetail billDetail = BillDetail.builder()
                            .service(service)
                            .price(service.getPrice())
                            .build();
                    billDetails.add(billDetail);
                    // total service price
                    totalServicePrice += service.getPrice();
                }
                // total room price
                Float totalRoomPrice = (owner.getApartment().getArea() * setting.getRoomPricePerM2())
                        * (100 + setting.getRoomVat())
                        / 100;

                // create new bill
                Bill newBill = Bill.builder()
                        .monthly(currentMonthly)
                        .totalPrice(totalRoomPrice + totalServicePrice)
                        .oldWater(owner.getApartment().getCurrentWaterNumber())
                        .status(BillStatus.UNPAID)
                        .relationship(owner)
                        .billDetails(billDetails)
                        .build();

                // Link each BillDetail to the newly created Bill
                for (BillDetail billDetail : billDetails) {
                    billDetail.setBill(newBill);
                }

                billRepository.save(newBill);
            }
        }

        setting.setSystemStatus(SystemStatus.PREPAYMENT);
        settingRepository.save(setting);

        return settingMapper.entityToResponse(setting);
    }

    @Override
    public SettingResponse updateStatusPayment() {
        Setting setting = getSetting();

        // check bills have already been calculated water price
        List<Bill> bills = billRepository.findByDeletedAtIsNullAndMonthlyAndNewWaterIsNull(setting.getCurrentMonthly());

        if (!bills.isEmpty()) {
            throw new AppException(ErrorCode.WATER_NOT_RECORDED);
        }

        setting.setSystemStatus(SystemStatus.PAYMENT);

        return settingMapper.entityToResponse(settingRepository.save(setting));
    }

    @Override
    public SettingResponse updateStatusOverdue() {
        Setting setting = getSetting();

        List<Bill> bills =
                billRepository.findByDeletedAtIsNullAndMonthlyAndStatus(setting.getCurrentMonthly(), BillStatus.UNPAID);

        for (Bill bill : bills) {
            bill.setStatus(BillStatus.OVERDUE);
        }

        billRepository.saveAll(bills);

        setting.setSystemStatus(SystemStatus.OVERDUE);

        return settingMapper.entityToResponse(settingRepository.save(setting));
    }

    @Override
    public SettingResponse updateStatusDelinquent() {
        Setting setting = getSetting();

        List<Relationship> owners =
                relationshipRepository.findDelinquentOwners(RelationshipRole.OWNER, BillStatus.OVERDUE);

        for (Relationship owner : owners) {
            owner.getApartment().setStatus(ApartmentStatus.DISRUPTION);
            apartmentRepository.save(owner.getApartment());
        }

        setting.setSystemStatus(SystemStatus.DELINQUENT);

        return settingMapper.entityToResponse(settingRepository.save(setting));
    }
}
