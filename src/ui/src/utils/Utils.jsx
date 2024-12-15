export default function getCurrency(number) {
  const formatter = new Intl.NumberFormat("en-SG", {
    style: "currency",
    currency: "SGD",
  });
  return formatter.format(number);
}
