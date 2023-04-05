open Graphics;;
module L=List;;
module A=Array;;


let iof= int_of_float ;;
let foi = float_of_int ;;

let sqr v = v*.v;;
let dist (x,y) (x',y') = sqrt ((sqr (x-. x')) +. (sqr (y -. y')));;
let perimetre_abc (a, b, c)= (dist a b) +. (dist b c) +. (dist c a);;
let edges_abc (a, b, c) = [a,b; b,c; c,a];;
let edges triangles = L.flatten (L.map edges_abc triangles);;
let longuest_edge liste_triangles =
	let l= edges liste_triangles in
	L.fold_left (fun (p,q) (a,b) -> let pq=dist p q in
					let ab=dist a b in
			if pq > ab then (p,q) else (a,b))
			(L.hd l) (L.tl l);;
let ijk_contient_ab (i,j,k) (a,b) =
	(i=a || j=a || k=a)
&&	(i=b || j=b || k=b);;

let subdivide_un_triangle liste_triangles lmax (a, b) =
	let xa,ya= a in let xb,yb= b in
	let xm,ym= (xa+.xb)/.2., (ya+.yb)/.2. in
	let m= (xm,ym) in
	let avec_ab= L.filter 
	 	 (function (i,j,k)-> ijk_contient_ab (i,j,k) (a,b)) 
		 liste_triangles in
	let sans_ab= L.filter
		(function (i,j,k)-> not (ijk_contient_ab (i,j,k) (a,b)))
		liste_triangles in
	let rec subdiv (i,j,k) (a,b) =
		if i <> a then subdiv (j,k,i) (a,b)
		else if j<>b then subdiv (i,k,j) (a,b)
		else [ a,m,k; k,m,b]
	in
	(L.flatten (L.map (function (i,j,k)->subdiv (i,j,k) (a,b)) avec_ab))
	@ sans_ab ;;

let rec subdivide liste_triangles lmax =
	let (a,b)= longuest_edge liste_triangles in 
	let ab= dist a b in
	if ab < lmax then liste_triangles
	else subdivide (subdivide_un_triangle liste_triangles lmax (a,b)) lmax ;;

let go liste_triangles lmax=
	let l=subdivide liste_triangles lmax in
	clear_graph();
	L.iter (function ((xa,ya), (xb,yb), (xc,yc))-> 
			moveto (iof xa) (iof ya);
			lineto (iof xb) (iof yb);
			lineto (iof xc) (iof yc);
			lineto (iof xa) (iof ya)
		) l;;

open_graph " 512x512";;

let a= 10., 10.;;
let b= 100., 10.;;
let c= 350., 400.;;
let labc= [ a,b,c ];;
go labc 90.;;

	(* je n'ai pas besoin de ca... 
	let cercle_inscrit (a,b,c)=
	match (a,b,c) with (xa,ya),(xb,yb),(xc,yc) ->
	let ab= dist a b in
	let bc= dist b c in
	let ca= dist a c in
	let masse= ab +. bc +. ca in
	let xC= (ab*.xc +. bc*.xa +. ca*.xb)/. (1. *. masse) in
	let yC= (ab*.yc +. bc*.ya +. ca*.yb)/. (1. *. masse) in
	let centre= xC, yC in
	(xC, yC, rC);;
	*)
let rec sum i1 i2 f = if i1=i2 then f i1 else (f i1) +. sum (i1+1) i2 f;;
let matrice nlig ncol f= 
	A.init nlig (function l-> A.init ncol (function c-> f l c));;
let minor mat l c =
	let n= A.length mat in
	matrice (n-1) (A.length mat.(0)-1) (fun lig col ->
	  mat.(if lig < l then lig else lig+1).(if col<c then col else col+1));;
let rec determinant m =
if A.length m= 1 then m.(0).(0)
else sum 0 (A.length m - 1)
	(function col -> (if 0=col mod 2 then 1. else (-1.)) 
		*. m.(0).(col) *.  determinant (minor m 0 col));;
let dist2 (x,y) (x',y') = (sqr (x-.x')) +. sqr (y-.y');;
let incircle p0 p1 p2 p3=
	let d01= dist2 p0 p1 in let d02= dist2 p0 p2 in let d03= dist2 p0 p3 in
	let d12= dist2 p1 p2 in let d13= dist2 p1 p3 in 
	let d23= dist2 p2 p3 in
	let m= [| [| 0.; d01; d02; d03 |];
		  [| d01; 0.; d12; d13 |];
		  [| d02; d12; 0.; d23 |];
		  [| d03; d13; d23; 0. |]|] in
	determinant m < 0.;;
(*
incircle (1., 0.) (-0.5, (sqrt 3.)/.2.)  (-0.5, 0. -. (sqrt 3.)/.2.) (0.,0.);;
returns true 
*)
let empty a b c tab =
	let rec go i =
	if i=A.length tab then true
	else if tab.(i)=a then go (i+1) 
	else if tab.(i)=b then go (i+1)
	else if tab.(i)=c then go (i+1)
	else (not (incircle a b c tab.(i))) && go (i+1)
in go 0;;
let delaunay points=
let tab= A.of_list points in
let n= A.length tab in
let l= ref [] in
for a=0 to n-3 do
	for b=a+1 to n-2 do 
		for c=b+1 to n-1 do
			if empty tab.(a) tab.(b) tab.(c) tab
			then l := (tab.(a),tab.(b),tab.(c)):: !l
		done 
	done
done;
!l ;;

let rec uniq l = match l with [] | [_] -> l
	| p0::p1::q -> if p0=p1 then uniq (p0::q) else p0::(uniq (p1::q));;
let triangule liste_triangles lmax=
        let l=subdivide liste_triangles lmax in
	let pts = L.flatten (L.map (function (a,b,c)->[a;b;c]) l) in
	let pts = L.sort (fun (x,y)(x',y')-> let dx=x-.x' in
			let dy=y-.y' in
		if compare dx 0. = 0 then compare dy 0.  else compare dx 0.) pts in
	let pts = uniq pts in
	let triangles = delaunay pts in
        clear_graph();
        L.iter (function ((xa,ya), (xb,yb), (xc,yc))->
                        moveto (iof xa) (iof ya);
                        lineto (iof xb) (iof yb);
                        lineto (iof xc) (iof yc);
                        lineto (iof xa) (iof ya)
                ) triangles;
	triangles
;;

triangule labc 100. ;;
(*
#use"triangle.ml";;
*)
