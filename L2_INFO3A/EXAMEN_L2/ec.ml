let phi = ( 1. +. sqrt 5.) /. 2.;;
let phi' = (1. -. sqrt 5.) /. 2.;;
let rec puiss a k=
	if k=0 then 1.
	else if k=1 then a
	else if 0= k mod 2 then puiss (a *. a) (k/2)
	else a *. (puiss a (k-1));;
let iof=int_of_float;;
let foi=float_of_int;;
let puissance a n = iof( puiss (foi a) n);;

module A=Array;;
A.init 10 (function k -> ((puiss phi k) -. (puiss phi' k)) /. (sqrt 5.)) ;;

let troisk= A.init 15 (puiss 3.);;
let deuxk= A.init 15 (puiss 2.);;
let tablo = A.make 15 1.;;
for i=1 to 14 do tablo.(i) <- deuxk.(i) +. 3. *. tablo.(i-1)
done;;

A.init 14 (function k -> troisk.(k+1) -. deuxk.(k+1) );;
