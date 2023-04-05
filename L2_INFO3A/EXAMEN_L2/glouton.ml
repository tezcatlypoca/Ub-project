(*
on colore des graphes dont les sommets sont les entiers naturels

arete dans graphe 1: pgcd a b > 1 <=> arete entre a et b
	couleur.(2*k)==k
	mais la couleur des nbs impairs est moins claire!

arete dans graphe 2: a divise b ou le contraire. Ici il y a une formule qui donne la couleur, il faut ecrire la decomposition de sommet en nb premiers et considerer les puissances: 2^a3^b5^c... c'est la somme des exposants  (1 est colorie en 0). Je l'ai prouvé mais pas vérifié.

arete dans graphe 3: pgcd a b >= 1 <=> arete entre a et b
*)

module A=Array;;
let rec pgcd a b = if b=0 then a else pgcd b (a mod b);;
let arete1 i j = pgcd i j > 1;;
let arete2 i j = i<>j && min i j <> 0 && 0=(max i j) mod (min i j) ;;
let arete3 i j = pgcd i j > 2;;

let rec couleur_possible arete tabcol sommet coul=
	let rec ieme_est_compatible i =
		i=sommet ||
		(
                        (	(not (arete i sommet)) || tabcol.(i) <> coul)
			&& ieme_est_compatible (1+i)
		) in
	ieme_est_compatible 0;;

let rec trouve_couleur arete tabcol sommet couleur  =
	if couleur_possible arete tabcol sommet couleur 
	then couleur
	else trouve_couleur arete tabcol  sommet (1+couleur);;

let greedy arete n =
	let tabcol= A.make n (-1) in
	for sommet=0 to n-1 do
		tabcol.(sommet) <- trouve_couleur arete tabcol sommet 0 
	done;
	tabcol;;

let subtab tab a b (* tab.(a*i+b) *) =
	let n=A.length tab in
	A.init (n/a) (function i -> tab.(a*i+b));;

let co1= greedy arete1 2000;;
for s=0 to A.length co1 - 1 do
	if 0= s mod 2 then
	if co1.(s) <> s/2 then Printf.printf "contre-exemple trouve: couleur de %d =%d\n"  s co1.(s) 
done;;


subtab co1 2 0;; (* [|0; 1; 2; 3; 4; 5; 6; 7; 8; 9; 10; ... |] *)
subtab co1 2 1;;
subtab co1 3 0;;
subtab co1 3 1;;
let tmp=subtab co1 3 2;;
for i=0 to A.length tmp - 1 do
	if tmp.(i) mod 3 <> 1 then Printf.printf "tmp.(%d)=%d mod 3\n" i (tmp.(i) mod 3)
done;;

let co2= greedy arete2 2000;;
subtab co2 2 0;;
subtab co2 2 1;;
subtab co2 3 0;;

let co3= greedy arete3 2000;;
