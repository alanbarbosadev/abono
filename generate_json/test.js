let linha =
  "21,000000000001,01012022,2022, 3254573980,                                                       Carlos Fabrício,31121969,00000000001,                                                       Roberta Barbosa,01,000474.60,08,05022024,31122024,00000000000000000000,0000,42179,4,PP,408332091541405,3,0000000";

let meuArray = linha.split(",");

total = 0;
for (let elemento of meuArray) {
  console.log(elemento, ":", elemento.length);
  total = total + elemento.length;
}

console.log("total :", total);
