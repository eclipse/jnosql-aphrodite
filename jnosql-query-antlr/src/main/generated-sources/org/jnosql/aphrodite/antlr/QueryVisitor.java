// Generated from org/jnosql/aphrodite/antlr/Query.g4 by ANTLR 4.7.1
package org.jnosql.aphrodite.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link QueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface QueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link QueryParser#select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect(QueryParser.SelectContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(QueryParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#insert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert(QueryParser.InsertContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(QueryParser.UpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#get}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGet(QueryParser.GetContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#del}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDel(QueryParser.DelContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#put}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPut(QueryParser.PutContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFields(QueryParser.FieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#deleteFields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteFields(QueryParser.DeleteFieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#star}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar(QueryParser.StarContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#skip}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(QueryParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#limit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit(QueryParser.LimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder(QueryParser.OrderContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#orderName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderName(QueryParser.OrderNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(QueryParser.WhereContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(QueryParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#eq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(QueryParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#gt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGt(QueryParser.GtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#gte}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGte(QueryParser.GteContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#lt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(QueryParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#lte}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLte(QueryParser.LteContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#between}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween(QueryParser.BetweenContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(QueryParser.InContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#like}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike(QueryParser.LikeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(QueryParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#asc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsc(QueryParser.AscContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#desc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesc(QueryParser.DescContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(QueryParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(QueryParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#ttl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTtl(QueryParser.TtlContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#unit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnit(QueryParser.UnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#changes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChanges(QueryParser.ChangesContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#change}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChange(QueryParser.ChangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#key}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey(QueryParser.KeyContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#keys}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeys(QueryParser.KeysContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(QueryParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(QueryParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#entity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntity(QueryParser.EntityContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(QueryParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(QueryParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#convert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConvert(QueryParser.ConvertContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(QueryParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(QueryParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(QueryParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJson(QueryParser.JsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#object_json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_json(QueryParser.Object_jsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#pair_json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair_json(QueryParser.Pair_jsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#value_json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue_json(QueryParser.Value_jsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#array_json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_json(QueryParser.Array_jsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link QueryParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(QueryParser.ParameterContext ctx);
}