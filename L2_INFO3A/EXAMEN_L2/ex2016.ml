module A=Array;;
module L=List;;

let matrice nl nc f = 
	A.init nl (function l -> A.init nc (function c -> f l c));;

let rec somme i0 i1 fi =
        assert( i0 <= i1);
        if i0=i1 then fi i0 else (fi i0) + (somme (1+i0) i1 fi ) ;;

let prod_mat a b =
        let la= A.length a in let ca = A.length a.(0) in
        let lb= A.length b in let cb = A.length b.(0) in
        assert( ca = lb);
        matrice la cb (fun l c -> somme 0 (ca-1) (function k -> a.(l).(k) * b.(k).(c)));;

let rec puiss_mat a k =
        let n= A.length a in
        assert( A.length a.(0) = n);
        if k=0 then matrice n n (fun l c -> if c=l then 1 else 0)
        else if 1=k then a
        else if 0 = k mod 2 then puiss_mat (prod_mat a a) (k/2) else prod_mat a (puiss_mat a (k-1));;

let m= [| [| 2; 1; 2 |]; [| 0; 1; 1 |]; [| 0; 0; 1 |]|];;
A.init 10 (puiss_mat m);;

let rec puiss_nb a k =
	if 0=k then 1 
	else if 0 = k mod 2 then puiss_nb (a*a) (k/2)
	else a * puiss_nb a (k-1);;
let conjecture n = [| [| puiss_nb 2 n; (puiss_nb 2 n)-1; 3*(puiss_nb 2 n)-n-3   |];
		      [| 0; 1; n |]; [| 0;0; 1 |] |];;
		 
(* cette matrice intervient dans  partiel_2016.pdf;
donc m^n = 2^n  ; 2^n-1;  3*2^n - n - 3
             0;       1;              n
             0;       0;              1
*)
