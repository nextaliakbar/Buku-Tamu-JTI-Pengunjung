package com.webapps.repository;

import com.webapps.entity.*;

public interface InstitutionGuestRepository {

    InstitutionGuest create(InstitutionGuest institutionGuest);

    Guest createGuest(Guest guest);

    Address createAddress(Address address, String guestId);

    Institution createInstitution(Institution institution, String guestId);

    Need createNeed(Need need, String guestId);
}
