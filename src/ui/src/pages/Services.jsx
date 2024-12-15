import React, { useLayoutEffect, useState } from "react";
import getCurrency from "../utils/Utils";

const Services = () => {
  const [services, setServices] = useState([]);

  useLayoutEffect(() => {
    const getServices = async () => {
      const res = await fetch("api/services");
      const services = await res.json();
      setServices(services);
    };

    try {
      getServices();
    } catch (e) {
      console.log(`error fetching services: ${services}`);
    }
  });

  return (
    <table>
      <thead>
        <tr>
          <th>Service Id</th>
          <th>Name</th>
          <th>Price</th>
        </tr>
      </thead>
      <tbody>
        {services.map((service) => {
          const { serviceId, name, price } = service;
          return (
            <tr key={serviceId}>
              <th>{serviceId}</th>
              <th>{name}</th>
              <th>{getCurrency(price)}</th>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default Services;
