import React, { useLayoutEffect, useState } from "react";
import getCurrency from "../utils/Utils";

const Products = () => {
  const [products, setProducts] = useState([]);

  useLayoutEffect(() => {
    const getProducts = async () => {
      const res = await fetch("/api/products/all");
      const products = await res.json();
      setProducts(products);
    };

    try {
      getProducts();
    } catch (e) {
      console.log(`error fetching products: ${e}`);
    }
  }, []);

  return (
    <table>
      <thead>
        <tr>
          <th>Product ID</th>
          <th>Name</th>
          <th>Price</th>
          <th>Vendor Id</th>
          <th>Vendor Name</th>
        </tr>
      </thead>
      <tbody>
        {products.map((product) => {
          const { productId, name, price, vendorId, vendorName } = product;
          return (
            <tr key={productId}>
              <td>{productId}</td>
              <td>{name}</td>
              <td>{getCurrency(price)}</td>
              <td>{vendorId}</td>
              <td>{vendorName}</td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default Products;
