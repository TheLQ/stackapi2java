/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thelq.stackexchange.api.queries.site;

import java.util.Collection;
import java.util.Collections;
import lombok.NonNull;
import org.joda.time.DateTime;
import org.thelq.stackexchange.api.model.types.AnswerEntry;
import org.thelq.stackexchange.api.model.types.BadgeEntry;
import org.thelq.stackexchange.api.model.types.CommentEntry;
import org.thelq.stackexchange.api.model.types.InboxItemEntry;
import org.thelq.stackexchange.api.model.types.NotificationEntry;
import org.thelq.stackexchange.api.model.types.PrivlegeEntry;
import org.thelq.stackexchange.api.model.types.QuestionEntry;
import org.thelq.stackexchange.api.model.types.ReputationEntry;
import org.thelq.stackexchange.api.model.types.ReputationHistoryEntry;
import org.thelq.stackexchange.api.model.types.SuggestedEditEntry;
import org.thelq.stackexchange.api.model.types.TagEntry;
import org.thelq.stackexchange.api.model.types.TopTagEntry;
import org.thelq.stackexchange.api.model.types.UserEntry;
import org.thelq.stackexchange.api.model.types.UserTimelineEntry;
import org.thelq.stackexchange.api.model.types.WritePermissionEntry;
import org.thelq.stackexchange.api.queries.methods.QueryMethod;
import org.thelq.stackexchange.api.queries.methods.SimpleQueryMethod;
import org.thelq.stackexchange.api.queries.methods.VectorQueryMethod;
import org.thelq.stackexchange.api.queries.site.sort.BadgeSort;
import org.thelq.stackexchange.api.queries.site.sort.CommentSort;
import org.thelq.stackexchange.api.queries.site.sort.PostSort;
import org.thelq.stackexchange.api.queries.site.sort.QuestionFavoriteSort;
import org.thelq.stackexchange.api.queries.site.sort.SuggestedEditSort;
import org.thelq.stackexchange.api.queries.site.sort.TagSort;
import org.thelq.stackexchange.api.queries.site.sort.UserSort;

/**
 *
 * @author Leon
 */
public class UserQueries {
	public static Collection<Integer> ME_IDS = Collections.unmodifiableCollection(Collections.<Integer>emptyList());
	public static Integer ME_ID = new Integer(0);

	public static <Q extends AbstractComplexFullQuery<UserSort<?>, Q, UserEntry>> Q all() {
		return new AbstractComplexFullQuery<UserSort<?>, Q, UserEntry>(UserEntry.class, new SimpleQueryMethod("users"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<UserSort<?>, Q, UserEntry>> Q all(String inname) {
		return new AbstractComplexFullQuery<UserSort<?>, Q, UserEntry>(UserEntry.class, new SimpleQueryMethod("users"))
				.setParameter("inname", inname);
	}

	public static <Q extends AbstractComplexFullQuery<UserSort<?>, Q, UserEntry>> Q byIds(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<UserSort<?>, Q, UserEntry>(UserEntry.class, createUserQueryMethod("", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>> Q badges(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<BadgeSort<?>, Q, BadgeEntry>(BadgeEntry.class, createUserQueryMethod("badges", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q answers(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, createUserQueryMethod("answers", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q comments(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, createUserQueryMethod("comments", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q commentsToUser(Collection<Integer> userIds, int told) {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, createUserQueryMethod("comments/{}", userIds))
				.setAuthRequired(isMe(userIds))
				.setParameter("told", told);
	}

	public static <Q extends AbstractComplexFullQuery<QuestionFavoriteSort<?>, Q, QuestionEntry>> Q favorites(Collection<Integer> userIds, int told) {
		return new AbstractComplexFullQuery<QuestionFavoriteSort<?>, Q, QuestionEntry>(QuestionEntry.class, createUserQueryMethod("favorites", userIds))
				.setAuthRequired(isMe(userIds))
				.setParameter("told", told);
	}

	public static <Q extends AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>> Q mentioned(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<CommentSort<?>, Q, CommentEntry>(CommentEntry.class, createUserQueryMethod("mentioned", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractSitePagableQuery<Q, NotificationEntry>> Q notifications(Integer userId) {
		return new AbstractSitePagableQuery<Q, NotificationEntry>(NotificationEntry.class, createUserQueryMethod("notifications", userId))
				.setAuthRequired(true);
	}

	public static <Q extends AbstractSitePagableQuery<Q, NotificationEntry>> Q notificationsUnread(Integer userId) {
		return new AbstractSitePagableQuery<Q, NotificationEntry>(NotificationEntry.class, createUserQueryMethod("notifications/unread", userId))
				.setAuthRequired(true);
	}

	public static <Q extends AbstractSitePagableQuery<Q, PrivlegeEntry>> Q privileges(Integer userId) {
		return new AbstractSitePagableQuery<Q, PrivlegeEntry>(PrivlegeEntry.class, createUserQueryMethod("notifications/unread", userId))
				.setAuthRequired(isMe(userId));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questions(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, createUserQueryMethod("questions", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsFeatured(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, createUserQueryMethod("questions/featured", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsUanswered(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, createUserQueryMethod("questions/uanswered", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsNoAnswers(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, createUserQueryMethod("questions/no-answers", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q questionsUnaccepted(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, createUserQueryMethod("questions/unaccepted", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexDateQuery<Q, ReputationEntry>> Q reputation(Collection<Integer> userIds) {
		return new AbstractComplexDateQuery<Q, ReputationEntry>(ReputationEntry.class, createUserQueryMethod("reputation", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractSitePagableQuery<Q, ReputationHistoryEntry>> Q reputationHistory(Collection<Integer> userIds) {
		return new AbstractSitePagableQuery<Q, ReputationHistoryEntry>(ReputationHistoryEntry.class, createUserQueryMethod("reputation-history", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractSitePagableQuery<Q, ReputationHistoryEntry>> Q reputationHistoryFull(Integer userId) {
		return new AbstractSitePagableQuery<Q, ReputationHistoryEntry>(ReputationHistoryEntry.class, createUserQueryMethod("reputation-history/full", userId))
				.setAuthRequired(true);
	}

	public static <Q extends AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>> Q suggestedEdits(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<SuggestedEditSort<?>, Q, SuggestedEditEntry>(SuggestedEditEntry.class, createUserQueryMethod("suggested-edits", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>> Q tags(Collection<Integer> userIds) {
		return new AbstractComplexFullQuery<TagSort<?>, Q, TagEntry>(TagEntry.class, createUserQueryMethod("tags", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, AnswerEntry>> Q tagsTopAnswers(Integer userId, Collection<String> tags) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, AnswerEntry>(AnswerEntry.class, createUserQueryMethod("tags/{}/top-answers", userId, tags))
				.setAuthRequired(isMe(userId));
	}

	public static <Q extends AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>> Q tagsTopQuestions(Integer userId, Collection<String> tags) {
		return new AbstractComplexFullQuery<PostSort<?>, Q, QuestionEntry>(QuestionEntry.class, createUserQueryMethod("tags/{}/top-questions", userId, tags))
				.setAuthRequired(isMe(userId));
	}

	public static <Q extends AbstractComplexDateQuery<Q, UserTimelineEntry>> Q timeline(Collection<Integer> userIds) {
		return new AbstractComplexDateQuery<Q, UserTimelineEntry>(UserTimelineEntry.class, createUserQueryMethod("timeline", userIds))
				.setAuthRequired(isMe(userIds));
	}

	public static <Q extends AbstractSitePagableQuery<Q, TopTagEntry>> Q topAnswerTags(Integer userId) {
		return new AbstractSitePagableQuery<Q, TopTagEntry>(TopTagEntry.class, createUserQueryMethod("top-answer-tags", userId))
				.setAuthRequired(isMe(userId));
	}

	public static <Q extends AbstractSitePagableQuery<Q, TopTagEntry>> Q topQuestionTags(Integer userId) {
		return new AbstractSitePagableQuery<Q, TopTagEntry>(TopTagEntry.class, createUserQueryMethod("top-question-tags", userId))
				.setAuthRequired(isMe(userId));
	}

	public static <Q extends AbstractSitePagableQuery<Q, WritePermissionEntry>> Q writePermissions(Integer userId) {
		return new AbstractSitePagableQuery<Q, WritePermissionEntry>(WritePermissionEntry.class, createUserQueryMethod("write-permissions", userId))
				.setAuthRequired(isMe(userId));
	}

	public static <Q extends AbstractComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>> Q moderators() {
		return new AbstractComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>(WritePermissionEntry.class, new SimpleQueryMethod("users/moderators"))
				.self();
	}

	public static <Q extends AbstractComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>> Q moderatorsElected() {
		return new AbstractComplexFullQuery<UserSort<?>, Q, WritePermissionEntry>(WritePermissionEntry.class, new SimpleQueryMethod("users/moderators"))
				.self();
	}

	public static <Q extends AbstractSitePagableQuery<Q, InboxItemEntry>> Q inbox(int userId) {
		return new AbstractSitePagableQuery<Q, InboxItemEntry>(InboxItemEntry.class, createUserQueryMethod("inbox", userId))
				.setAuthRequired(true);
	}

	public static <Q extends AbstractSitePagableQuery<Q, InboxItemEntry>> Q inboxUnread(Integer userId) {
		return new AbstractSitePagableQuery<Q, InboxItemEntry>(InboxItemEntry.class, createUserQueryMethod("inbox/unread", userId))
				.setAuthRequired(true);
	}

	public static <Q extends AbstractSitePagableQuery<Q, InboxItemEntry>> Q inboxUnread(Integer userId, DateTime since) {
		return new AbstractSitePagableQuery<Q, InboxItemEntry>(InboxItemEntry.class, createUserQueryMethod("inbox/unread", userId))
				.setAuthRequired(true)
				.setParameter("since", since.getMillis());
	}

	protected static boolean isMe(Integer userId) {
		return userId == ME_ID;
	}

	protected static boolean isMe(Collection<Integer> userIds) {
		return userIds == ME_IDS;
	}

	protected static SimpleQueryMethod createUserQueryMethod(@NonNull String userRaw, @NonNull Integer userId) {
		if (isMe(userId))
			return new SimpleQueryMethod("me/" + userRaw);
		else
			return new SimpleQueryMethod("users/" + userId + "/" + userRaw);
	}

	protected static VectorQueryMethod createUserQueryMethod(@NonNull String userRaw, @NonNull Integer userId, @NonNull Collection<?> vectorCollection) {
		if (isMe(userId))
			return new VectorQueryMethod("me/" + userRaw, vectorCollection);
		else
			return new VectorQueryMethod("users/" + userId + "/" + userRaw, vectorCollection);
	}

	protected static QueryMethod createUserQueryMethod(@NonNull String userRaw, @NonNull Collection<Integer> userIds, @NonNull String... vectorSingle) {
		if (isMe(userIds))
			if (vectorSingle.length == 0)
				return new SimpleQueryMethod("me/" + userRaw);
			else
				return new VectorQueryMethod("me/" + userRaw, vectorSingle);
		else
			return new VectorQueryMethod("users/{}/" + userRaw, vectorSingle);
	}
}
