(* IDEM nim.ml
MAIS (0,0) EST PERDANT
*)
#load"graphics.cma";;
module H= Hashtbl;;
module L= List;;

let ht_gagnant = H.create 1000;;
H.add ht_gagnant (0,0) false;;

let rec foldint i1 i2 f =
	if i1 > i2 then [ ]
	else (f i1) :: (foldint (i1+1) i2 f);;

let voisins (x,y) =
     ( foldint 1 x (function i -> x-i,y) )
@    ( foldint 1 y (function i -> x, y-i) )
@    ( foldint 1 (min x y) (function i -> x-i,y-i)) ;;

let rec gagnant (x,y) =
	try H.find ht_gagnant (x,y)
	with Not_found -> 
	(let neighbors = voisins (x,y) in
	 let resultat = ([] = L.filter gagnant neighbors ) in
	 H.add ht_gagnant (x,y) resultat; resultat
	);;

let les_gagnants n =
let liste = ref [] in
for x=0 to n do for y=0 to n do
	if gagnant (x,y) then liste := (x,y):: !liste
done done;
L.rev !liste;;

open Graphics;;
let dessin n =
	let toscreen (x,y)=
		10 + x *(500/n), 10 + y *(500/n) in
	clear_graph ();
	for x=0 to n do
		let x',y' = toscreen (x, 0) in moveto x' y'; 
		let x'',y'' = toscreen (x, n) in lineto x'' y'';
		moveto x' y';
		let (x''',y''')= toscreen (x+n-x, n-x) in
		lineto x''' y'''
	done;
	for y=0 to n do
		let x',y' = toscreen (0, y)  in moveto x' y';
		let x'',y'' =  toscreen (n, y) in lineto x'' y'';
		moveto x' y';
		let (x''',y''')= toscreen (n-y, n) in 
		lineto x''' y'''
	done;
	for x=0 to n do for y=0 to n do 
		if  gagnant (x,y) 
		then let (x', y')=toscreen (x,y) in fill_circle x' y' 4 
	done done;
;;

let l200 = les_gagnants 200;;
let l200' = L.filter (function (x,y) -> x <= y ) l200;;
let rec ecarts l = match l with
| [] | [_] -> []
| (x,y)::(x',y')::q -> (x'-x,y'-y)::(ecarts (L.tl l));;
let e200 = ecarts l200';;
open_graph " 512x512";;
