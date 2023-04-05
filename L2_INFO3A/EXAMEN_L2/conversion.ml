(* conversion expression postfixe -> arbre binaire *)
type operation= Plus | Mult;;
type item = Nb of float | String of string | Op of operation;;
type postfix = item list;;

type abin = Fnb of float | Fstring  of string | Node of abin*operation*abin;;
let rec convert exp pile = match exp with 
| [] -> (match pile with [arbre] -> arbre | _ -> failwith "bad expression")
| (Nb n)::q -> convert q ((Fnb n)::pile)
| (String nom)::q -> convert q ((Fstring nom)::pile)
| (Op op)::q -> (match pile with b::a::reste -> convert q ((Node (a, op, b))::reste)
                                 | _ -> failwith "another bad expression");;

convert [ Nb 1.; Nb 2.; Nb 3.; Op Mult; Op Plus ] [];;
convert [ Nb 1.; Nb 2.; Nb 3.; Op Mult; Op Plus; Nb 5. ; Op Mult ] [];;

let rec verspostfixe arbre= match arbre with
| Fnb nb -> [Nb nb ]
| Fstring chaine -> [String chaine]
| Node( g, op, d) ->  (verspostfixe g) @ (verspostfixe d) @ [Op op];;

verspostfixe (Node (Fnb 1., Plus, Node (Fnb 2., Mult, Fnb 3.)));;
verspostfixe (Node (Node (Fnb 1., Plus, Node (Fnb 2., Mult, Fnb 3.)), Mult, Fnb 5.));;


