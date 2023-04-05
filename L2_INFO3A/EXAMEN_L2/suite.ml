open List;;
let rec suite a b n =
	if 0=n then a
	else if 1=n then b
	else 3 * (suite a b (n-1)) - 2 * (suite a b (n-2));;

let rec puiss x k =
	if 0=k then 1
	else if 1=k then x
	else x * (puiss x (k-1));;

let suite2 a b n =
	a + a - b + (b-a) * puiss 2 n ;;

let rec iaj i j= if i=j then [i] else i::(iaj (i+1) j);;

let l= iaj 0 10;;
let sl = map (suite 1 2) l;;
let sl'= map (suite2 1 2) l;;

map2 ( - ) sl sl';;
map2 ( - ) (map (suite 1 0) l) (map (suite2 1 0) l);;
map2 ( - ) (map (suite 0 1) l) (map (suite2 0 1) l);;

let l = iaj 0 20;;
let s1_2 = map (suite 1 2) l;;
let s1_0 = map (suite 1 0) l;;
let s0_1 = map (suite 0 1) l;;
let s1_1 = map (suite 1 1) l;;
let s_1_1 = map (suite (-1) 1) l;;
