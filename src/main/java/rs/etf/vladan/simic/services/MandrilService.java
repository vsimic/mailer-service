package rs.etf.vladan.simic.services;

import rs.etf.vladan.simic.domains.VerificationEmail;

public interface MandrilService {

	public boolean sendVerificationMail(VerificationEmail verificationEmail);

}
