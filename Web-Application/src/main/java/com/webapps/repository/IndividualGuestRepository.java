package com.webapps.repository;

import com.webapps.entity.Address;
import com.webapps.entity.Guest;
import com.webapps.entity.IndividualGuest;
import com.webapps.entity.Need;

public interface IndividualGuestRepository {

    IndividualGuest create(IndividualGuest individualGuest);

    Guest createGuest(Guest guest);

    Address createAddress(Address address, String guestId);

    Need createNeed(Need need, String guestId);
}
