import React, { useLayoutEffect, useState } from "react";

const Vendors = () => {
  const [vendors, setVendors] = useState([]);

  useLayoutEffect(() => {
    const getVendors = async () => {
      const res = await fetch("/api/vendors");
      const vendors = await res.json();
      setVendors(vendors);
    };

    try {
      getVendors();
    } catch (e) {
      console.log(`error fetching vendors: ${e}`);
    }
  });

  return (
    <table>
      <thead>
        <tr>
          <th>Vendor Id</th>
          <th>Name</th>
          <th>Contact</th>
          <th>Phone Number</th>
          <th>Email Address</th>
          <th>Address</th>
        </tr>
      </thead>
      <tbody>
        {vendors.map((vendor) => {
          const {
            vendorId,
            name,
            contact,
            phoneNumber,
            emailAddress,
            address,
          } = vendor;
          return (
            <tr key={vendorId}>
              <th>{vendorId}</th>
              <th>{name}</th>
              <th>{contact}</th>
              <th>{phoneNumber}</th>
              <th>{emailAddress}</th>
              <th>{address}</th>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default Vendors;
