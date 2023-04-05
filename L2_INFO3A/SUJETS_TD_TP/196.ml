(* on part d'un nombre
   on retourne son ecriture en bse 10
   on fait la somme
ceci jusq'a ce que ce soit un palindrome
J'ai lu qq part qu'on ignore si ca s'arrete pour 196

ca se programme facilement
*)

open Num;;

let envers chaine=
let n= String.length chaine in
let ch= String.make n ' ' in
for i=0 to n-1 do ch.[i] <- chaine.[n-1-i] done;
ch ;;

let palindromic ch = (ch = envers ch);;

let f196 nb =
let rec go k nb =
	if k mod 1000 = 0 || palindromic (string_of_num nb) then 
	( Printf.printf "%d: %s\n" k (string_of_num nb);
	  flush stdout );
	if palindromic (string_of_num nb) then nb 
	else let s'= nb +/ (num_of_string (envers (string_of_num nb))) in
	go (k+1) s'
in Printf.printf "==================\n"; go 0 (Int nb);;

for i=0 to 195 do f196 i done;;
for i=197 to 200 do f196 i done;;
for i=200 to 300 do f196 i done;;
for i=300 to 393 do f196 i done;;
for i=395 to 500 do f196 i done;;
for i=500 to 600 do f196 i done;;
